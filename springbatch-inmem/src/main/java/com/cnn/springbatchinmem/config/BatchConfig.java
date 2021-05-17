/**
 * Copyright(c) 2011-2021 by YouCredit Inc. All Rights Reserved
 */
package com.cnn.springbatchinmem.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 基于内存模式
 * 
 * @author stxyg
 * @date 2021/5/11
 */
@Slf4j
@Configuration
public class BatchConfig {
    @Bean
    public ResourcelessTransactionManager platformTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public MapJobRepositoryFactoryBean jobRepositoryFactoryBean(PlatformTransactionManager platformTransactionManager) {
        MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean = new MapJobRepositoryFactoryBean();
        mapJobRepositoryFactoryBean.setTransactionManager(platformTransactionManager);
        return mapJobRepositoryFactoryBean;
    }

    @Bean
    public JobLauncher simpleJobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        return simpleJobLauncher;
    }

    @Bean
    public MapJobExplorerFactoryBean jobExplorer(MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean) {
        MapJobExplorerFactoryBean mapJobExplorerFactoryBean = new MapJobExplorerFactoryBean();
        mapJobExplorerFactoryBean.setRepositoryFactory(mapJobRepositoryFactoryBean);
        return mapJobExplorerFactoryBean;
    }

    @Bean
    public MapJobRegistry mapJobRegistry() {
        return new MapJobRegistry();
    }

    /**
     * step工厂
     *
     * @param jobRepository
     * @param transactionManager
     * @return
     */
    @Bean
    public StepBuilderFactory stepBuilderFactory(JobRepository jobRepository,
        PlatformTransactionManager transactionManager) {
        return new StepBuilderFactory(jobRepository, transactionManager);
    }

    /**
     * job工厂
     *
     * @param jobRepository
     * @return
     */
    @Bean
    public JobBuilderFactory jobBuilderFactory(JobRepository jobRepository) {
        return new JobBuilderFactory(jobRepository);
    }
}
