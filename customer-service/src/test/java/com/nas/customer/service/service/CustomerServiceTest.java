package com.nas.customer.service.service;

import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayload;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.Assert;
import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.command.CustomerInfoUpdateCmd;
import com.nas.customer.service.criteria.CustomerCriteria;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.repository.CustomerRepository;
import com.nas.customer.service.service.customer.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@Slf4j
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {


    /**
     * @InjectMocks, the phrase inject might be deceptive.
     * This is a Mockito utility that relieves us of the responsibility of constructing an instance of the class under test.
     * In a word, Mockito will look for a suitable public function Object() { [native code] } to create an instance of our
     * CustomerServiceImpl and pass it all mocks (there is just one).
     */
    @InjectMocks
    private CustomerServiceImpl customerService;

    /**
     * we have @Mock annotation on
     * Client class because we need client class in our test but we do not want to invoke the original class.
     * so, we 'Mock' that class which works the same as the original but is actually just a mock
     *
     */
    @Mock
    private CustomerRepository customerRepository;


    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this); // this is needed for init of mocks, if you use @Mock
    }
    @Test
    public void can_i_get_all_customer(){
        PageRequest pageRequest = PageRequest.of(0, 10);
        final List<Customer> customers = Arrays.asList(
                Customer.create(new CustomerCommand()),
                Customer.create(new CustomerCommand()),
                Customer.create(new CustomerCommand())
        );
        final Page<Customer> customers1 = new PageImpl<>(customers);
        when(customerRepository.findAll(pageRequest)).thenReturn(customers1);

        customerService.findAllByDeletedFalse(pageRequest);
        assertThat(customerRepository.findAll(pageRequest).getContent().size()).isEqualTo(customers.size());
    }
    @Test
    public void should_can_create_customer_with_valid_payload(){
        final CustomerCommand customerCommand = new CustomerCommand();
        customerCommand.setFirstName("anas");
        customerCommand.setLastName("abbal");
        customerCommand.setPassword("zadina123");
        customerCommand.setEmail("anas.abbal10@gmail.com");

        final Customer customer = Customer.create(customerCommand);

        when(customerRepository.save(Customer.create(customerCommand))).thenReturn(customer);
        customerService.create(customerCommand);

        assertThat(customerRepository.save(customer).getFirstName()).isEqualTo("anas");
    }
    @Test
    public void should_can_i_get_customer_with_id(){
        final CustomerCommand customerCommand = new CustomerCommand();
        customerCommand.setFirstName("Anas");
        customerCommand.setLastName("abbal");
        customerCommand.setPassword("anas123");
        customerCommand.setEmail("anas.abbal10@gmail.com");

        final Customer customer = Customer.create(customerCommand);
        customer.setId("1");
        doReturn(Optional.of(customer)).when(customerRepository).findById("1");

        final Customer customer1 = customerService.findById("1");
        assertEquals(customerRepository.findById("1"), Optional.of(customer));
        assertEquals(customer.getFirstName(), "Anas");
        assertEquals(customer1.getLastName(), "abbal");
    }
    @Test
    public void should_can_i_update_customer_with_id(){
        final CustomerCommand customerCommand = new CustomerCommand();
        customerCommand.setFirstName("Anas");
        customerCommand.setLastName("abbal");
        customerCommand.setPassword("anas123");
        customerCommand.setEmail("anas.abbal10@gmail.com");

        final Customer customer = Customer.create(customerCommand);
        customer.setId("1");

        final CustomerInfoUpdateCmd customerCommand1 = new CustomerInfoUpdateCmd();
        customerCommand1.setFirstName("Nassoft");
        customerCommand1.setLastName("Nassoft");
        customerCommand1.setEmail("nassoft.abbal10@gmail.com");

        doReturn(Optional.of(customer)).when(customerRepository).findById("1");
        when(customerRepository.save(any(Customer.class))).thenReturn(any(Customer.class));

        customerService.updateInfo(customerCommand1, "1");

        assertThat(customer.getFirstName()).isNotEmpty();
        assertEquals(customer.getFirstName(), "Nassoft");
    }

    @Test
    public void should_can_get_get_all_by_criteria(){
        PageRequest pageRequest = PageRequest.of(0, 10);

        final CustomerCommand customerCommand = new CustomerCommand();
        customerCommand.setFirstName("Anas");
        customerCommand.setLastName("abbal");
        customerCommand.setPassword("anas123");
        customerCommand.setEmail("anas.abbal10@gmail.com");

        final CustomerCommand customerCommand1 = new CustomerCommand();
        customerCommand1.setFirstName("Nassoft");
        customerCommand1.setLastName("Nassoft");
        customerCommand1.setEmail("nassoft.abbal10@gmail.com");

        final List<Customer> customers = Arrays.asList(
                Customer.create(customerCommand),
                Customer.create(customerCommand1)
        );
        // Criteria
        final CustomerCriteria customerCriteria = new CustomerCriteria("Nassoft");

        final Page<Customer> customerPage = new PageImpl<>(customers);

        when(customerRepository.findAllByCriteria(pageRequest, customerCriteria)).thenReturn(customerPage);
        customerService.getAllByCriteria(pageRequest, customerCriteria);
        assertEquals(customerService.getAllByCriteria(pageRequest, customerCriteria).getSize(), 2);
    }
}
