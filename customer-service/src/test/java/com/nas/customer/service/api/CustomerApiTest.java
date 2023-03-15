package com.nas.customer.service.api;


import com.nas.customer.service.controller.CustomerController;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.repository.CustomerRepository;
import com.nas.customer.service.service.customer.CustomerService;
import com.nas.customer.service.service.customer.CustomerServiceImpl;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private List<Customer> customers;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeClass
    public void setUp() throws Exception {
        //Initialize all mock objects
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, restTemplate, rabbitTemplate);
    }


    @Test
    public void can_i_create_customer_api(){
    }
    @Test
    @DisplayName("Test Find All Customers")
    public void find_all_customers() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Customer> customersList = customers;
        Page<Customer> pageCustomer = new PageImpl<>(customersList);

        // when
        when(customerService.findAllByDeletedFalse(pageRequest)).thenReturn(pageCustomer);

        // then
        mockMvc.perform(get("/v1/customers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // verify
        verify(customerService, times(1)).findAllByDeletedFalse(pageRequest);
    }
}
