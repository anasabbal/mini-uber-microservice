package com.nas.driver.service.driver;


import com.nas.core.details.BankAccount;
import com.nas.core.details.DriverLocationDto;
import com.nas.core.details.WalletDetails;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.driver.command.DriverCommand;
import com.nas.driver.command.RatingCommand;
import com.nas.driver.criteria.DriverCriteria;
import com.nas.driver.model.Driver;
import com.nas.driver.payload.DriverDetails;
import com.nas.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{


    private final DriverRepository driverRepository;
    private final RestTemplate restTemplate;


    @Override
    public Driver create(DriverCommand driverCommand) {
        driverCommand.validate();
        log.info("Begin creating driver with payload {}", JSONUtil.toJSON(driverCommand));
        final Driver driver = Driver.create(driverCommand);
        log.info("Driver with id {} created successfully", driver.getId());
        driverRepository.save(driver);
        final String uri = "http://DRIVER-LOCATION:8082/v1/driver-location/" + driver.getId();
        log.info("[+] URI => {}", uri);
        restTemplate.getForObject(
                uri,
                String.class,
                driver.getId());
        return driver;
    }
    private <T> ResponseEntity<T> getEntity(String url, Class<T> eClass){
        return restTemplate.getForEntity(url, eClass);
    }
    @Override
    public DriverDetails getDriverDetailsByDriverId(String driverId) {
        final Driver driver = findById(driverId);

        final ResponseEntity<DriverLocationDto> driverLocationDtoResponseEntity = getEntity(
                "http://DRIVER-LOCATION:8082/v1/driver-location/driver-location-details/"
                        + driverId,
                DriverLocationDto.class
        );
        var driverResponse = driverLocationDtoResponseEntity.getBody();
        final ResponseEntity<BankAccount> bankAccountResponseEntity = getEntity(
                "http://PAYMENT:2345/v1/payment/account-details/"
                        + driverId,
                BankAccount.class
        );
        var bankAccountResponse = bankAccountResponseEntity.getBody();
        final ResponseEntity<WalletDetails> walletDetailsResponseEntity = getEntity(
                "http://WALLET:2000/v1/wallet/payment/" +
                        bankAccountResponse.getId(),
                WalletDetails.class
        );
        return new DriverDetails(
                driverId,
                driver.getFirstName(),
                driver.getLastName(),
                driverResponse,
                bankAccountResponse,
                walletDetailsResponseEntity.getBody()
        );
    }
    @Override
    public void deleteAccount(String driverId) {
        log.info("[+] Begin removing account with id {}", driverId);
        final Driver driver = findById(driverId);
        driverRepository.delete(driver);
        restTemplate.delete("http://DRIVER-LOCATION:8082/v1/driver-location/" + driverId, driverId);
    }
    /*@Override
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listenToMessage(CustomerRequestDriver payload){
        log.info(String.format("[+] Received message -> %s", JSONUtil.toJSON(payload)));
        final Driver driver = findById(payload.getDriverId());
        log.info("[+] Begin creating notification for driver with id {}", driver.getId());
        driver.addToDriver(payload);
        driverRepository.save(driver);
    }*/

    @Override
    public Page<Driver> findAllByCriteria(Pageable pageable, DriverCriteria driverCriteria) {
        return driverRepository.findAllByCriteria(pageable, driverCriteria);
    }

    @Override
    public String sendRating(RatingCommand ratingCommand) {
        final Driver driver = findById(ratingCommand.getDriverId());
        final String customerId = driver.getLastNotification();
        if(ratingCommand.getCustomerId().equals(customerId)) {
            restTemplate.postForEntity(
                    "http://RATING:2018/v1/ratings/",
                    ratingCommand,
                    RatingCommand.class
            );
            return "Message Sent";
        }
        else{
            return "Message Not Sent";
        }
    }

    @Override
    public void update(String driverId, DriverCommand driverCommand) {
        driverCommand.validate();
        log.info("[+] Begin updating driver with id {}", driverId);
        final Driver driver =findById(driverId);
        log.info("[+] Begin updating driver with payload {}", JSONUtil.toJSON(driverCommand));
        driver.updateInfo(driverCommand);
        log.info("[+] Driver with id {} updated successfully", driver.getId());
        driverRepository.save(driver);
    }
    @Override
    public Set<Driver> getDriversAvailable() {
        return driverRepository.findByDriverStatusStatus("AVAILABLE");
    }
    @Override
    public Driver findById(String driverId){
        log.info("Begin fetching driver with id {}", driverId);
        final Driver driver = driverRepository.findById(driverId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_NOT_FOUND.get()));
        log.info("Driver with id {} fetched successfully", driverId);
        return driver;
    }
    @Override
    public Page<Driver> getAll(Pageable pageable) {
        return driverRepository.findAllByDeletedFalse(pageable);
    }
}

