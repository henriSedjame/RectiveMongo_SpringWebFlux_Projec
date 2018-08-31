package com.shj.mongoreact.Repository;

import com.shj.mongoreact.Model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String>, QuerydslPredicateExecutor<Employee> {
}
