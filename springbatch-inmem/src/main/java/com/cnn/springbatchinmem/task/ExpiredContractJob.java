package com.cnn.springbatchinmem.task;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

/**
 * job
 * 
 * @author stxyg
 * @date 2021/5/10 15:27
 */
@Component
public class ExpiredContractJob {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, Step step) {
        return jobBuilderFactory.get("expiredContractJob").start(step).build();
    }

    /**
     * 读
     * 
     * @param dataSource
     * @return
     */
    @Bean
    public ItemReader<?> itemReader(DataSource dataSource) {
        JdbcCursorItemReader<Object> itemReader = new JdbcCursorItemReader<>();
        itemReader.setSql("sql");
        BeanPropertyRowMapper<Object> rowMapper = new BeanPropertyRowMapper<>();
        rowMapper.setMappedClass(Object.class);
        itemReader.setRowMapper(rowMapper);
        itemReader.setDataSource(dataSource);
        return itemReader;
    }

    /**
     * 
     * @return
     */
    @Bean
    public ItemWriter<Object> itemWriter(List<?> var1) {
        // xxxx
        JdbcBatchItemWriter itemWriter = new JdbcBatchItemWriter();
        itemWriter.setSql("xxxx");
        return itemWriter;
    }

    /**
     * step
     * 
     * @param reader
     * @param itemWriter
     * @return
     */
    @Bean
    protected Step step(StepBuilderFactory stepBuilderFactory, ItemReader<?> reader, ItemWriter<Object> itemWriter) {
        return stepBuilderFactory.get("step").chunk(100000).reader(reader).writer(itemWriter)
            .listener(new BatchStepListener<>()).build();
    }

}
