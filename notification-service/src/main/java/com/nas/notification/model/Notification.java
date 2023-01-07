package com.nas.notification.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Notification {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    protected String id;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "DELETED")
    protected Boolean deleted = false;

    @Column(name = "ACTIVE")
    protected Boolean active = true;

    @Column(name = "DRIVER_ID")
    private String driverId;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "IS_NOTIFICATION")
    private Boolean isNotification;
}
