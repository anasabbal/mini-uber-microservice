package com.nas.carrier.model;


import com.nas.carrier.command.ApplicationSubmitCommand;
import com.nas.carrier.command.JobCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Job extends BaseEntity{


    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "job")
    public Set<ApplicationSubmit> applicationSubmitSet;


    public static Job create(final JobCommand jobCommand){
        final Job job = new Job();

        job.description = jobCommand.getDescription();
        job.applicationSubmitSet = createPayloadForApplicationSubmitted(jobCommand.getApplicationSubmitCommands());

        return job;
    }
    public static Set<ApplicationSubmit> createPayloadForApplicationSubmitted(final Set<ApplicationSubmitCommand> applicationSubmitCommands){
        return applicationSubmitCommands.stream().map(ApplicationSubmit::create).collect(Collectors.toSet());
    }
}
