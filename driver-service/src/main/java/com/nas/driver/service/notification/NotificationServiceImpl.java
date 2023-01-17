package com.nas.driver.service.notification;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.driver.model.Driver;
import com.nas.driver.model.NotificationDriver;
import com.nas.driver.repository.DriverRepository;
import com.nas.driver.repository.NotificationDriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{


    private final NotificationDriverRepository notificationDriverRepository;
    private final DriverRepository driverRepository;


    @Override
    public Page<NotificationDriver> getNotificationsByDriverId(Pageable pageable, String driverId) {
        log.info("Begin fetching driver with id {}", driverId);
        final Driver driver = driverRepository.findById(driverId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_NOT_FOUND.get())
        );
        return notificationDriverRepository.findAllByDriverId(pageable, driver.getId());
    }
}
