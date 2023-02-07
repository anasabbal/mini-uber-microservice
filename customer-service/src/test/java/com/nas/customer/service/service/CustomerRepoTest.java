package com.nas.customer.service.service;

import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.repository.CustomerRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CustomerRepoTest {


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
    private Customer customer;

    @Mock
    private CustomerCommand customerCommand;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this); // this is needed for init of mocks, if you use @Mock
        customerCommand = new CustomerCommand();
        customerCommand.setFirstName("anas");
        customerCommand.setLastName("abbal");
        customerCommand.setPassword("zadina123");
        customerCommand.setEmail("anas.abbal10@gmail.com");
        customer = Customer.create(customerCommand);
    }

    @Test
    public void should_can_i_create_customer(){
        //when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        //assertEquals("anas" , customer.getFirstName());

        //verify(customerRepository, times(1)).save(any(Customer.class));
        //assertEquals("anas.abbal10@gmail.com", customer1.getEmail());
    }


    @Test
    public void should_can_i_find_customer_by_true_id(){

    }
    @Test
    public void should_can_i_find_customer_by_false_id(){

    }
    @Test
    public void should_can_i_update_without_validate(){

    }
    @Test
    public void should_i_find_all_customers(){
        final CustomerCommand customerCommand = new CustomerCommand();
        customerCommand.setEmail("anas.abbal10@gmail.com");
        when(customerRepository.findAll()).
                thenReturn(Arrays.asList(Customer.create(customerCommand)));
        List<Customer> customers = customerService.findAll();

        assertEquals("anas.abbal10@gmail.com", customers.get(0).getEmail());
    }
    @Test
    public void should_can_i_update_with_wrong_payload(){

    }
}
