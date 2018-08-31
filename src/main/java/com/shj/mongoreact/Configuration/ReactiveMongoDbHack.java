package com.shj.mongoreact.Configuration;

import com.shj.mongoreact.Repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories(repositoryFactoryBeanClass =  CustomReactiveMongoRepositoryFactoryBean.class )
@Configuration
public class ReactiveMongoDbHack {

}
