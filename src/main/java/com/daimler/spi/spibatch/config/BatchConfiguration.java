package com.daimler.spi.spibatch.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.daimler.spi.spibatch.data.EntityObj;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public JpaPagingItemReader<EntityObj> reader() {
        JpaPagingItemReader<EntityObj> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("select m from EntityObj m");
        reader.setPageSize(10);
        return reader;
    }

    @Bean
    public FlatFileItemWriter<EntityObj> writer() {
        FlatFileItemWriter<EntityObj> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("extract.txt"));
        writer.setLineAggregator(new DelimitedLineAggregator<EntityObj>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<EntityObj>() {{
                setNames(new String[]{"id", "data"});
            }});
        }});
        

        return writer;
    }

    @Bean
    public Step exportCustomerStep() {
        return stepBuilderFactory.get("spiBatchStep")
                .<EntityObj, EntityObj>chunk(10)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    public Job exportCustomerJob() {
        return jobBuilderFactory.get("spiBatchJob")
        		.incrementer(new RunIdIncrementer())
                .start(exportCustomerStep())
                .build();
    }
}
