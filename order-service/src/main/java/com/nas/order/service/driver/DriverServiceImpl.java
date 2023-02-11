package com.nas.order.service.driver;


import com.nas.core.exception.ExceptionPayload;
import com.nas.order.data.DriverData;
import com.nas.order.data.UserData;
import com.nas.order.repository.DriverDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{

    
    private final DriverDataRepository driverDataRepository;

    @Override
    public DriverData findOrderById(String id, ExceptionPayload exceptionPayload) {
        return driverDataRepository.getById(id);
    }
    @Override
    public List<DriverData> findOrders() {
        return driverDataRepository.findAll();
    }
    @SneakyThrows
    @Override
    public <C extends UserData> DriverData createOrder(C command, Class<DriverData> clazz) {
        final DriverData driverData = clazz.getDeclaredConstructor().newInstance();
        driverData.create(command);
        return driverDataRepository.save(driverData);
    }
}