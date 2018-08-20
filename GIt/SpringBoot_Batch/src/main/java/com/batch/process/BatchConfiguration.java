package com.batch.process;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import com.batch.model.Person;


    @Configuration
	@EnableBatchProcessing
	public class BatchConfiguration 
	{

	 @Autowired
	 public JobBuilderFactory jobBuilderFactory;
	 
	 @Autowired
	 public StepBuilderFactory stepBuilderFactory;
	 
	 @Autowired
	 public DataSource dataSource;
	 
	 @Bean
	 public DataSource dataSource() {
	  final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	  dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	  dataSource.setUrl("jdbc:mysql://localhost/ash2");
	  dataSource.setUsername("root");
	  dataSource.setPassword("ashish");
	  
	  return dataSource;
	 }
	 
	 @Bean
	 public FlatFileItemReader<Person> reader(){
	  FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
	  reader.setResource(new ClassPathResource("users.csv"));
	  reader.setLineMapper(new DefaultLineMapper<Person>() {{
	   setLineTokenizer(new DelimitedLineTokenizer() {{
	    setNames(new String[] { "name" });
	   }});
	   setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
	    setTargetType(Person.class);
	   }});
	   
	  }});
	  
	  return reader;
	 }
	 
	 @Bean
	 public PersonItemProcessor processor(){
	  return new PersonItemProcessor();
	 }
	 
	 @Bean
	 public JdbcBatchItemWriter<Person> writer(){
	  JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
	  writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
	  writer.setSql("INSERT INTO user(name) VALUES (:name)");
	  writer.setDataSource(dataSource);
	  
	  return writer;
	 }
	 
	 @Bean
	 public Step step1() {
	  return stepBuilderFactory.get("step1").<Person, Person> chunk(3)
	    .reader(reader())
	    .processor(processor())
	    .writer(writer())
	    .build();
	 }
	 
	 @Bean
	 public Job importUserJob() {
	  return jobBuilderFactory.get("importUserJob")
	    .incrementer(new RunIdIncrementer())
	    .flow(step1())
	    .end()
	    .build();
	 }

}
