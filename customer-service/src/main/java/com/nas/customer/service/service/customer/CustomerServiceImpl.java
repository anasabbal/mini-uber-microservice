package com.nas.customer.service.service.customer;


import com.nas.core.details.BankAccount;
import com.nas.core.details.DriverLocationDto;
import com.nas.core.details.WalletDetails;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.command.CustomerInfoUpdateCmd;
import com.nas.customer.service.command.CustomerRequestDriver;
import com.nas.customer.service.command.RatingCommand;
import com.nas.customer.service.criteria.CustomerCriteria;
import com.nas.customer.service.mapper.CustomerMapper;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.model.Driver;
import com.nas.customer.service.payload.CustomerDetails;
import com.nas.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    //private final RabbitTemplate rabbitTemplate;
    private final CustomerMapper customerMapper;
    //private final JmsTemplate jmsTemplate;


    @Override
    public Customer create(CustomerCommand customerCommand) {
        customerCommand.validate();
        log.info("[+] Begin creating customer with payload {}", JSONUtil.toJSON(customerCommand));
        final Customer customer = customerRepository.save(Customer.create(customerCommand));
        log.info("[+] Customer with id {} created successfully", JSONUtil.toJSON(customer.getId()));

        /*restTemplate.getForObject(
                "http://DRIVER-LOCATION:8082/v1/driver-location/" + customer.getId(),
                String.class,
                customer.getId()
        );*/
        return customer;
    }
    @Override
    public Page<Customer> findAllByDeletedFalse(Pageable pageable) {
        return customerRepository.findCustomersByDeletedFalse(pageable);
    }
    @Override
    public String sendRating(RatingCommand ratingCommand) {
        if(findById(ratingCommand.getDriverId()) == null)
            throw new RuntimeException("[+] Driver Not found");
        restTemplate.postForEntity(
                "http://localhost:8000/rating-service/v1/ratings", ratingCommand,
                RatingCommand.class
        );
        return "[+] Message Sent successfully !!";
    }
    @Override
    public Customer findById(String customerId) {
        log.info("[+] Begin fetching customer by id {}", customerId);
        final Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.CUSTOMER_NOT_FOUND.get())
        );
        log.info("[+] Customer with id {} fetched successfully", customer.getId());
        return customer;
    }

    @Override
    public Set<Driver> getDriversAvailable() {
        final ResponseEntity<Set<Driver>> objects = restTemplate.exchange(
                "https://nuber-microservice-driver-service.azuremicroservices.io/v1/drivers", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        log.info("[+] Drivers with payload {}", JSONUtil.toJSON(objects.getBody()));
        return objects.getBody();
    }
    @Override
    public void sendRequestDriver(CustomerRequestDriver requestDriver){
        getDriversAvailable().stream().filter(
                        dv -> dv.getId().equals(requestDriver.getDriverId()))
                .findAny().orElseThrow(
                        () -> new BusinessException(ExceptionPayloadFactory.DRIVER_LOCATION_NOT_FOUND.get())
                );
        log.info("[+] Begin sending message with payload {}", JSONUtil.toJSON(requestDriver));
        //rabbitTemplate.convertAndSend("customer.routingkey", requestDriver);
        log.info("[+] Message with payload {} send Good :)", JSONUtil.toJSON(requestDriver));
    }
    /*@RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void acceptationResponseFromDriver(ResponseDriver responseDriver){
        log.info("[+] Begin listening to message and get response with payload {}", JSONUtil.toJSON(responseDriver));
        final Customer customer = findById(responseDriver.getCustomerId());
        customer.linkNotification(responseDriver.getDriverId());
        customer.setDriverId(responseDriver.getDriverId());
        customerRepository.save(customer);
    }*/
    @Override
    public CustomerDetails findCustomerDetailsById(String customerId) {
        final Customer customer = findById(customerId);
        final ResponseEntity<DriverLocationDto> driverLocationDtoResponseEntity = getEntity(
                "http://DRIVER-LOCATION:8082/v1/driver-location/driver-location-details/"
                        + customerId,
                DriverLocationDto.class
        );
        var driverResponse = driverLocationDtoResponseEntity.getBody();
        final ResponseEntity<BankAccount> bankAccountResponseEntity = getEntity(
                "http://PAYMENT:2345/v1/payment/account-details/"
                        + customerId,
                BankAccount.class
        );
        var bankAccountResponse = bankAccountResponseEntity.getBody();
        final ResponseEntity<WalletDetails> walletDetailsResponseEntity = getEntity(
                "http://WALLET:2000/v1/wallet/payment/" +
                        bankAccountResponse.getId(),
                WalletDetails.class
        );
        var walletDetailsResponse = walletDetailsResponseEntity.getBody();
        return new CustomerDetails(
                customerMapper.toDto(customer),
                driverResponse,
                bankAccountResponse,
                walletDetailsResponse);
    }
    private  <T> ResponseEntity<T> getEntity(String url, Class<T> eClass){
        return restTemplate.getForEntity(url, eClass);
    }

    @Override
    public Page<Customer> getAllByCriteria(Pageable pageable, CustomerCriteria customerCriteria) {
        return customerRepository.findAllByCriteria(pageable, customerCriteria);
    }

    @Override
    public void updateInfo(CustomerInfoUpdateCmd customerCommand, String customerId) {
        customerCommand.validate();
        final Customer customer = findById(customerId);
        customer.updateInfo(customerCommand);
        customerRepository.save(customer);
    }
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
}
