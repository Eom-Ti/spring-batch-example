package com.example.springbatchexample.configuration.batch;

import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBatchConfig {

    @Bean
    public ExecutionContextSerializer jacksonSerializer() {
        return new Jackson2ExecutionContextStringSerializer();
    }
}