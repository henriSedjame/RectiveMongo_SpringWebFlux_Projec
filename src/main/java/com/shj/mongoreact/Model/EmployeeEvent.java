package com.shj.mongoreact.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Project Reactive Mongo and Spring webFlux Project
 * @Author Henri Joel SEDJAME
 * @Date 01/09/2018
 * @Class purposes : .......
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeEvent {

  private Employee employee;
  private Date date;
}
