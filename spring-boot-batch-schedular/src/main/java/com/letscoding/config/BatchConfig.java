package com.letscoding.config;

import com.letscoding.model.Person;
import com.letscoding.processor.PersonItemProcessor;
import com.letscoding.tasklet.FileDeletingTasklet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    @Override
    public void setDataSource(DataSource dataSource) {
        // initialize will use a Map based JobRepository (instead of database)
    }

    @Bean
    public Job convertNamesJob(JobBuilderFactory jobBuilders,
                               StepBuilderFactory stepBuilders) {
        return jobBuilders.get("capitalizeNamesJob")
                .start(convertNamesStep(stepBuilders)).build();
    }

    @Bean
    public Step convertNamesStep(StepBuilderFactory stepBuilders) {
        return stepBuilders.get("capitalizeNamesStep")
                .<Person, Person>chunk(10).reader(itemReader())
                .processor(itemProcessor()).writer(itemWriter()).build();
    }

    @Bean
    public FlatFileItemReader<Person> itemReader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("csv/persons.csv"))
                .delimited().names(new String[] {"firstName", "lastName"})
                .targetType(Person.class).build();
    }

    @Bean
    public PersonItemProcessor itemProcessor() {
        return new PersonItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<Person> itemWriter() {
        return new FlatFileItemWriterBuilder<Person>()
                .name("personItemWriter")
                .resource(new FileSystemResource(
                        "target/test-outputs/persons.txt"))
                .delimited().delimiter(", ")
                .names(new String[] {"firstName", "lastName"}).build();
    }


}