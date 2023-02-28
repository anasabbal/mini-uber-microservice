package com.nas.carrier.model;


import com.nas.carrier.command.CarrierCommand;
import com.nas.carrier.command.JobCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Carrier extends BaseEntity{


    @OneToMany
    private Set<Job> jobs;

    public static Carrier create(final CarrierCommand carrierCommand){
        final Carrier carrier = new Carrier();

        carrier.jobs = createSetOfJobs(carrierCommand.getJobCommands());
        return carrier;
    }
    public static Set<Job> createSetOfJobs(Set<JobCommand> jobCommands){
        return jobCommands.stream().map(Job::create).collect(Collectors.toSet());
    }
}
