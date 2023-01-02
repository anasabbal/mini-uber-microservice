package com.nas.customer.service.service;

import com.nas.customer.service.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerRepoTest {


    @Mock
    private CustomerRepository customerRepository;

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.initMocks(this); // this is needed for init of mocks, if you use @Mock
        
    }


    @Test
    public void should_can_i_create_customer(){

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
    public void should_can_i_update_with_wrong_payload(){

    }
}
