package com.example.springbatchexample.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class ExampleJobConfiguration2 {

    @Bean(name = "exampleJob2")
    public Job exampleJob2(JobRepository jobRepository, @Qualifier("example2Step1") Step example2Step1,
                           @Qualifier("example2Step2") Step example2Step2) {
        return new JobBuilder("exampleJob2", jobRepository)
                .start(example2Step1)
                .start(example2Step2)
                .build();
    }

    @Bean
    public Step example2Step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("example2Step1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("example2 Step1 executed");
                    return RepeatStatus.FINISHED;
                }, transactionManager).build();
    }

    @Bean
    public Step example2Step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("example2Step2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("example2 Step2 executed");
                    return RepeatStatus.FINISHED;
                }, transactionManager).build();
    }
}
