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
public class ExampleJobConfiguration3 {

    @Bean(name = "exampleJob3")
    public Job exampleJob3(JobRepository jobRepository, @Qualifier("example3Step1") Step example3Step1,
                           @Qualifier("example3Step2") Step example3Step3) {
        return new JobBuilder("exampleJob3", jobRepository)
                .start(example3Step1)
                .start(example3Step3)
                .build();
    }

    @Bean
    public Step example3Step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("example3Step1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("example3 Step1 executed");
                    return RepeatStatus.FINISHED;
                }, transactionManager).build();
    }

    @Bean
    public Step example3Step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("example3Step2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("example3 Step2 executed");
                    return RepeatStatus.FINISHED;
                }, transactionManager).build();
    }
}
