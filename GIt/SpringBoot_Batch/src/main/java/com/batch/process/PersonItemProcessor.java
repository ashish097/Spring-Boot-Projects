package com.batch.process;

import org.springframework.batch.item.ItemProcessor;

import com.batch.model.Person;

public class PersonItemProcessor  implements ItemProcessor<Person, Person>{
	

		 @Override
		 public Person process(Person user) throws Exception {
		  return user;
		 }

}
