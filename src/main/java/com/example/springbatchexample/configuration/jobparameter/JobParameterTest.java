package com.example.springbatchexample.configuration.jobparameter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobParameterTest implements ApplicationRunner {

    private final JobLauncher jobLauncher;
    private final Job job;

    public JobParameterTest(JobLauncher jobLauncher, @Qualifier("exampleJob") Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("name", "user2")
                .addLong("seq", 3L)
                .addDate("date", new Date())
                .addDouble("age", 15.7)
                .toJobParameters();

        jobLauncher.run(job, jobParameters);
    }
}
