package com.nas.carrier.service.job;


import com.nas.carrier.command.JobCommand;
import com.nas.carrier.model.Job;
import com.nas.carrier.repository.JobRepository;
import com.nas.core.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{


    private final JobRepository jobRepository;


    @Override
    public Job create(JobCommand jobCommand) {
        jobCommand.validate();
        log.info("[+] Begin creating job with payload {}", JSONUtil.toJSON(jobCommand));
        return jobRepository.save(Job.create(jobCommand));
    }
}
