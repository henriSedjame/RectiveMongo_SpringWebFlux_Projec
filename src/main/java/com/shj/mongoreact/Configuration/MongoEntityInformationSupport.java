package com.shj.mongoreact.Configuration;

import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import javax.annotation.Nullable;

public class MongoEntityInformationSupport {

  private MongoEntityInformationSupport() {}

  /**
   * Factory method for creating {@link MongoEntityInformation}.
   *
   * @param entity must not be {@literal null}.
   * @param idType can be {@literal null}.
   * @return never {@literal null}.
   */
  @SuppressWarnings("unchecked")
  static <T, ID> MongoEntityInformation<T, ID> entityInformationFor(MongoPersistentEntity<?> entity,
                                                                    @Nullable Class<?> idType) {

    Assert.notNull(entity, "Entity must not be null!");

    MappingMongoEntityInformation<T, ID> entityInformation = new MappingMongoEntityInformation<T, ID>(
            (MongoPersistentEntity<T>) entity, (Class<ID>) idType);

    return ClassUtils.isAssignable(Persistable.class, entity.getType())
            ? new PersistableMongoEntityInformation<>(entityInformation) : entityInformation;
  }
}
