package com.shj.mongoreact.Configuration;

import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.repository.support.ReactiveMongoRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class CustomReactiveMongoRepositoryFactoryBean extends ReactiveMongoRepositoryFactoryBean {

  public CustomReactiveMongoRepositoryFactoryBean(Class repositoryInterface) {
    super(repositoryInterface);
  }

  @Override
  protected RepositoryFactorySupport getFactoryInstance(ReactiveMongoOperations operations) {
    return new CustomReactiveMongoRepositoryFactory(operations);
  }
}
