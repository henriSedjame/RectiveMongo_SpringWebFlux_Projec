package com.shj.mongoreact.Configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.lang.NonNull;
@RequiredArgsConstructor
public class PersistableMongoEntityInformation<T,ID> implements MongoEntityInformation<T, ID> {
  private final @NonNull MongoEntityInformation<T, ID> delegate;

  /*
   * (non-Javadoc)
   * @see org.springframework.data.mongodb.repository.MongoEntityInformation#getCollectionName()
   */
  @Override
  public String getCollectionName() {
    return delegate.getCollectionName();
  }

  /*
   * (non-Javadoc)
   * @see org.springframework.data.mongodb.repository.MongoEntityInformation#getIdAttribute()
   */
  @Override
  public String getIdAttribute() {
    return delegate.getIdAttribute();
  }

  /*
   * (non-Javadoc)
   * @see org.springframework.data.repository.core.EntityInformation#isNew(java.lang.Object)
   */
  @Override
  @SuppressWarnings("unchecked")
  public boolean isNew(T t) {

    if (t instanceof Persistable) {
      return ((Persistable<ID>) t).isNew();
    }

    return delegate.isNew(t);
  }

  /*
   * (non-Javadoc)
   * @see org.springframework.data.repository.core.EntityInformation#getId(java.lang.Object)
   */
  @Override
  @SuppressWarnings("unchecked")
  public ID getId(T t) {

    if (t instanceof Persistable) {
      return ((Persistable<ID>) t).getId();
    }

    return delegate.getId(t);
  }

  /*
   * (non-Javadoc)
   * @see org.springframework.data.repository.core.support.PersistentEntityInformation#getIdType()
   */
  @Override
  public Class<ID> getIdType() {
    return delegate.getIdType();
  }

  /*
   * (non-Javadoc)
   * @see org.springframework.data.repository.core.support.EntityMetadata#getJavaType()
   */
  @Override
  public Class<T> getJavaType() {
    return delegate.getJavaType();
  }
}
