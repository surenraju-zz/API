package com.containersolutions.api.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.containersolutions.api.models.PeopleDTO;
import com.containersolutions.api.processor.PeopleProcessor;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FlatFileItemReader<PeopleDTO> reader() {
		FlatFileItemReader<PeopleDTO> reader = new FlatFileItemReader<PeopleDTO>();
		reader.setResource(new ClassPathResource("titanic.csv"));
		reader.setLinesToSkip(1);

		reader.setLineMapper(new DefaultLineMapper<PeopleDTO>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "survived", "passengerClass", "name", "sex", "agre",
								"siblingsOrSpousesAboard", "parentsOrChildrenAboard", "fare" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper() {
					{
						setTargetType(PeopleDTO.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public PeopleProcessor processor() {
		return new PeopleProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<PeopleDTO> writer() {
		JdbcBatchItemWriter<PeopleDTO> writer = new JdbcBatchItemWriter<PeopleDTO>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		writer.setSql(
				"INSERT INTO people (uuid, survived, passenger_class, name, sex, age, siblings_or_spouses_aboard, parents_or_children_aboard, fare) "
						+ "VALUES (:uuid, :survived, :passengerClass, :name, :sex, :age, :siblingsOrSpousesAboard, :parentsOrChildrenAboard, :fare)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public Job importUserJob(JobExecutionListener listener) {
		return jobBuilderFactory.get("importTitanicDataJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<PeopleDTO, PeopleDTO>chunk(10).reader(reader()).processor(processor())
				.writer(writer()).build();
	}
}
