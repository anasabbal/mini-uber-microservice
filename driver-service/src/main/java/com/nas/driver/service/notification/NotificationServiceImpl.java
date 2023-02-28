package com.nas.driver.service.notification;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.driver.command.AcceptRequestCustomer;
import com.nas.driver.model.Driver;
import com.nas.driver.model.DriverStatus;
import com.nas.driver.model.NotificationDriver;
import com.nas.driver.repository.DriverRepository;
import com.nas.driver.repository.NotificationDriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
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
        log.info("[+] Begin fetching driver with id {}", driverId);
        final Driver driver = driverRepository.findById(driverId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_NOT_FOUND.get())
        );
        return notificationDriverRepository.findAllByDriverId(driver.getId());
    }

    @Override
    public Driver acceptRequest(final AcceptRequestCustomer acceptRequestCustomer) {
        log.info("[+] Begin fetching driver with id {}", acceptRequestCustomer.getDriverId());
        final Driver driver = driverRepository.findById(acceptRequestCustomer.getDriverId()).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_NOT_FOUND.get()));
        log.info("[+] Driver with id {} fetched successfully", acceptRequestCustomer.getDriverId());

        // Update status
        driver.setDriverStatus(DriverStatus.create("ONE_ADULT"));

        final List<NotificationDriver> notificationDrivers = getNotificationsByDriverId(acceptRequestCustomer.getDriverId());
        notificationDrivers.stream().filter(nt -> nt.getId().equals(acceptRequestCustomer.getCustomerId())).forEach(driver::addToDriver);
        log.info("[+] Begin sending acceptation to customers...");
        rabbitTemplate.convertAndSend(acceptRequestCustomer);
        return driverRepository.save(driver);
    }
    @Override
    public Driver cancelRequest(final AcceptRequestCustomer acceptRequestCustomer){
        final Driver driver = driverRepository.findById(acceptRequestCustomer.getDriverId()).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_NOT_FOUND.get()));
        log.info("[+] Driver with id {} fetched successfully", acceptRequestCustomer.getDriverId());
        final NotificationDriver notificationDriver = notificationDriverRepository.findByCustomerIdAndDriver(acceptRequestCustomer.getCustomerId(), driver);
        driver.cancelRequestNotification(notificationDriver);
        return driver;
    }
    @Override
    public Page<NotificationDriver> findAllByDriverId(Pageable pageable, String driverId) {
        final Driver driver = driverRepository.findById(driverId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_NOT_FOUND.get()));
        log.info("Driver with id {} fetched successfully", driverId);
        return notificationDriverRepository.findAllByDriver(pageable, driver);
    }
    @Override
    public Page<NotificationDriver> getAll(Pageable pageable) {
        return notificationDriverRepository.findAll(pageable);
    }
}
