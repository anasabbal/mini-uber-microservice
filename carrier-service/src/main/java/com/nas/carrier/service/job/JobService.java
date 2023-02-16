package com.nas.carrier.service.job;

import com.nas.carrier.command.JobCommand;
import com.nas.carrier.model.Job;

public interface JobService {
    Job create(final JobCommand jobCommand);
}
