package com.nas.customer.service.repository;


import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;



@Slf4j
@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerRepoTest {

    @Autowired
    private CustomerRepository customerRepository;


    @BeforeEach
    public void setUp() {
        final CustomerCommand customerCommand = new CustomerCommand();
        customerCommand.setFirstName("Anas");
        customerCommand.setLastName("abbal");
        customerCommand.setPassword("anas123");
        customerCommand.setEmail("anas.abbal10@gmail.com");

        final CustomerCommand customerCommand1 = new CustomerCommand();
        customerCommand1.setFirstName("Nassoft");
        customerCommand1.setLastName("Nassoft");
        customerCommand1.setEmail("nassoft.abbal10@gmail.com");

        customerRepository.save(Customer.create(customerCommand));
        customerRepository.save(Customer.create(customerCommand1));
    }

    @Test
    public void should_i_get_all_customers(){
    
    }
}
