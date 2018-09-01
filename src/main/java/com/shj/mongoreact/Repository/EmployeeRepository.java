package com.shj.mongoreact.Repository;

import com.shj.mongoreact.Model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
}
