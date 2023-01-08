package com.nas.driver.service.notification;


import com.nas.driver.command.NotificationDriverRequest;
import com.nas.driver.model.NotificationDriver;
import com.nas.driver.repository.NotificationDriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationDriverServiceImpl implements NotificationDriverService{

    private final NotificationDriverRepository notificationDriverRepository;


    @Override
    public NotificationDriver create(NotificationDriverRequest notificationDriverRequest) {
        log.info("Begin creating notification for driver with customer id {}", notificationDriverRequest.getCustomerId());
        return notificationDriverRepository.save(NotificationDriver.create(notificationDriverRequest));
    }
}
