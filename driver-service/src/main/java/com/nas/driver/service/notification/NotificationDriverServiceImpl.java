package com.nas.driver.service.notification;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.driver.command.NotificationDriverRequest;
import com.nas.driver.model.Driver;
import com.nas.driver.model.NotificationDriver;
import com.nas.driver.repository.DriverRepository;
import com.nas.driver.repository.NotificationDriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationDriverServiceImpl implements NotificationDriverService{

    private final NotificationDriverRepository notificationDriverRepository;
    private final DriverRepository driverRepository;
    private final RestTemplate restTemplate;

    @Override
    public NotificationDriver create(NotificationDriverRequest notificationDriverRequest) {
        log.info("Begin creating notification for driver with customer id {}", notificationDriverRequest.getCustomerId());
        return notificationDriverRepository.save(NotificationDriver.create(notificationDriverRequest));
    }
    @Override
    @KafkaListener(id = "driver_id", topics = "topic1")
    public void listenWhiteHeader(ConsumerRecord<String, String> payload){

        log.info("Topic: {}", payload.topic());
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Part: {}", payload.partition());
        log.info("Order: {}", payload.value());

        final NotificationDriver notificationDriver = create(
                NotificationDriverRequest.create(
                        payload.value()
                )
        );
        final Driver driver = driverRepository.findById(payload.value()).orElseThrow(
                () -> new BusinessException(
                        ExceptionPayloadFactory.DRIVER_NOT_FOUND.get()
                )
        );
        log.info("Begin init notification with id {} to driver with id {}", notificationDriver.getId(), driver.getId());
        updateNotificationDriver(driver, notificationDriver);
        log.info("Notification created successfully with payload {}", JSONUtil.toJSON(notificationDriver));
    }
    @Override
    public boolean updateNotificationDriver(Driver driver,
                                             NotificationDriver notificationDriver){
        return driver.add(notificationDriver.getId());
    }
}
