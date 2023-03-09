package com.nas.customer.service.repository;


import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.service.customer.CustomerService;
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
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;



@Slf4j
@ExtendWith(MockitoExtension.class)
public class CustomerRepoTest {



}
