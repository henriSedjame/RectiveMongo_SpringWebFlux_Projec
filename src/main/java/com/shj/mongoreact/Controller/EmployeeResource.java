package com.shj.mongoreact.Controller;

import com.shj.mongoreact.Model.Employee;
import com.shj.mongoreact.Repository.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeResource {

  private EmployeeRepository employeeRepository;

  public EmployeeResource(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }
  @GetMapping("/all")
  Flux<Employee> getAll(){
    return employeeRepository.findAll();
  }
}
