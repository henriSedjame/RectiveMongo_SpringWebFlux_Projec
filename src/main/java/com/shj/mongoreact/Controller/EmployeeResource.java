package com.shj.mongoreact.Controller;

import com.shj.mongoreact.Model.Employee;
import com.shj.mongoreact.Model.EmployeeEvent;
import com.shj.mongoreact.Repository.EmployeeRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

//@RestController
//@RequestMapping("/rest/employee")
public class EmployeeResource {

  private EmployeeRepository employeeRepository;

  public EmployeeResource(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @GetMapping("/all")
  public Flux<Employee> getAll() {
    return employeeRepository.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Employee> getById(@PathVariable final String id) {
    return employeeRepository.findById(id);
  }

  @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<EmployeeEvent> getEvents(@PathVariable final String id) {
    return employeeRepository.findById(id)
      .flatMapMany(employee -> {

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
        Flux<EmployeeEvent> employeeEventFlux = Flux.fromStream(Stream.generate(() -> new EmployeeEvent(employee, new Date())));

        return Flux.zip(interval, employeeEventFlux)
          .map(Tuple2::getT2);
      });
  }


}
