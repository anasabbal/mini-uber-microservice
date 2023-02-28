package com.nas.carrier.model;


import com.nas.carrier.command.ApplicationSubmitCommand;
import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationSubmit extends BaseEntity{


    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "RESUME")
    private byte[] resume;
    @ManyToOne
    private Job job;


    public static ApplicationSubmit create(final ApplicationSubmitCommand applicationSubmitCommand){
        final ApplicationSubmit applicationSubmit = new ApplicationSubmit();

        applicationSubmit.fullName = applicationSubmitCommand.getFullName();
        applicationSubmit.email = applicationSubmitCommand.getEmail();

        return applicationSubmit;
    }
}
