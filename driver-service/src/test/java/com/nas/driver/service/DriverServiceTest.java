package com.nas.driver.service;



import com.nas.driver.model.Driver;
import com.nas.driver.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {


    @Mock // create mocks which are needed to support testing of class to be tested
    private DriverRepository driverRepository;

    @InjectMocks // create class instances which needs to be tested in test class
    private DriverServiceImpl driverService;


    @BeforeClass
    public void setUp(){
        //Initialize all mock objects
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void should_can_i_get_all_drivers(){
        PageRequest pageRequest = PageRequest.of(0, 10);

        List<Driver> employees = Arrays.asList(new Driver(), new Driver());
        Page<Driver> employees1 = new PageImpl<>(employees);

        Mockito.when(driverRepository.findAll(pageRequest)).thenReturn(employees1);
        driverService.getAll(pageRequest);

        assertThat(driverRepository.findAll(pageRequest).getContent().size()).isEqualTo(2);
    }
}
