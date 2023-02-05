package com.nas.carrier.model;


import com.nas.carrier.command.ApplicationSubmitCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationSubmit extends BaseEntity{


    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "EMAIL")
    private String email;


    @ManyToOne
    private Job job;


    public static ApplicationSubmit create(final ApplicationSubmitCommand applicationSubmitCommand){
        final ApplicationSubmit applicationSubmit = new ApplicationSubmit();

        applicationSubmit.fullName = applicationSubmitCommand.getFullName();
        applicationSubmit.email = applicationSubmitCommand.getEmail();

        return applicationSubmit;
    }
}
