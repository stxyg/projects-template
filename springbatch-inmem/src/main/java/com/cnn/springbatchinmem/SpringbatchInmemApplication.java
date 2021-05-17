package com.cnn.springbatchinmem;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbatchInmemApplication implements ApplicationRunner {
    @Resource
    private JobLauncher jobLauncher;
    @Resource
    private Job job;

    public static void main(String[] args) {
        SpringApplication.run(SpringbatchInmemApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.jobLauncher.run(this.job, new JobParameters());
    }
}
