package com.nas.driver.service.notification;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.driver.command.AcceptRequestCustomer;
import com.nas.driver.model.Driver;
import com.nas.driver.model.NotificationDriver;
import com.nas.driver.repository.DriverRepository;
import com.nas.driver.repository.NotificationDriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{


    private final NotificationDriverRepository notificationDriverRepository;
    private final DriverRepository driverRepository;
    private final RabbitTemplate rabbitTemplate;


    @Override
    public List<NotificationDriver> getNotificationsByDriverId(String driverId) {
        log.info("Begin fetching driver with id {}", driverId);
        final Driver driver = driverRepository.findById(driverId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_NOT_FOUND.get())
        );
        return notificationDriverRepository.findAllByDriverId(driver.getId());
    }

    @Override
    public Driver acceptRequest(String driverId, final AcceptRequestCustomer acceptRequestCustomer) {
        log.info("Begin fetching driver with id {}", driverId);
        final Driver driver = driverRepository.findById(driverId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_NOT_FOUND.get()));
        log.info("Driver with id {} fetched successfully", driverId);

        final List<NotificationDriver> notificationDrivers = getNotificationsByDriverId(driverId);
        notificationDrivers.stream().filter(nt -> nt.getId().equals(acceptRequestCustomer.getCustomerId())).forEach(driver::addToDriver);
        log.info("[+] Begin sending acceptation to customers...");
        rabbitTemplate.convertAndSend(driverId);
        return driverRepository.save(driver);
    }
}
