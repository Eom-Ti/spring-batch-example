package com.example.springbatchexample.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@Slf4j
@Configuration
public class ExampleJobConfiguration {

    @Bean
    public Job exampleJob(JobRepository jobRepository, Step exampleStep, Step exampleStep2) {
        return new JobBuilder("exampleJob", jobRepository)
                .start(exampleStep)
                .next(exampleStep2)
                .build();
    }

    @Bean
    public Step exampleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("exampleStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("====================================");
                    log.info(String.valueOf(contribution.getStepExecution().getJobParameters().getParameter("name")));
                    log.info(String.valueOf(contribution.getStepExecution().getJobParameters().getParameter("seq")));
                    log.info(String.valueOf(contribution.getStepExecution().getJobParameters().getParameter("date")));
                    log.info(String.valueOf(contribution.getStepExecution().getJobParameters().getParameter("age")));
                    log.info("====================================");
                    log.info("hello example spring batch this is step1");
                    log.info("====================================");
                    Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
                    return RepeatStatus.FINISHED;
                }, transactionManager).build();
    }

    @Bean
    public Step exampleStep2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("exampleStep2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("====================================");
                    log.info("hello example spring batch this is step2");
                    log.info("====================================");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
