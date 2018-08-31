package com.shj.mongoreact.Configuration;

import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.QuerydslMongoPredicateExecutor;
import org.springframework.data.mongodb.repository.support.ReactiveMongoRepositoryFactory;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.RepositoryFragment;

import javax.annotation.Nullable;
import java.io.Serializable;

import static org.springframework.data.querydsl.QuerydslUtils.QUERY_DSL_PRESENT;

public class CustomReactiveMongoRepositoryFactory extends ReactiveMongoRepositoryFactory {

  private static MongoOperations operations;
  private final MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext;

  public CustomReactiveMongoRepositoryFactory(ReactiveMongoOperations operations) {
    super(operations);
    this.mappingContext = operations.getConverter().getMappingContext();
  }

  public static void setOperations(MongoOperations operations){
    CustomReactiveMongoRepositoryFactory.operations = operations;
  }

  @Override
  protected RepositoryComposition.RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata) {

    RepositoryComposition.RepositoryFragments fragments = RepositoryComposition.RepositoryFragments.empty();

    boolean isQueryDslRepository = QUERY_DSL_PRESENT
            && QuerydslPredicateExecutor.class.isAssignableFrom(metadata.getRepositoryInterface());

    if (isQueryDslRepository) {

      MongoEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType(),
              metadata);

      fragments = fragments.append(RepositoryFragment.implemented(
              getTargetRepositoryViaReflection(QuerydslMongoPredicateExecutor.class, entityInformation, operations)));
    }

    return fragments;
  }

  private <T, ID> MongoEntityInformation<T, ID> getEntityInformation(Class<T> domainClass,
                                                                     @Nullable RepositoryMetadata metadata) {

    MongoPersistentEntity<?> entity = mappingContext.getRequiredPersistentEntity(domainClass);
    return MongoEntityInformationSupport.<T, ID> entityInformationFor(entity,
            metadata != null ? metadata.getIdType() : null);
  }
}
