package com.tryhomi.admin.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

@Configuration
public class TaskSchedulerConfiguration {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(20);
        return threadPoolTaskScheduler;
    }

    @Bean
    public DelegatingSecurityContextAsyncTaskExecutor taskExecutor() {
        return new DelegatingSecurityContextAsyncTaskExecutor(threadPoolTaskScheduler());
    }
}
