package com.shj.mongoreact.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @Project Reactive Mongo and Spring webFlux Project
 * @Author Henri Joel SEDJAME
 * @Date 01/09/2018
 * @Class purposes : .......
 */

@Configuration
public class ReactiveConfig {

  @Bean
  RouterFunction<?> routerFunction(RouterHandlers routerHandlers) {
    return RouterFunctions
      .route(RequestPredicates.GET("/rest/employee/all"), routerHandlers::getAll)
      .andRoute(RequestPredicates.GET("/rest/employee/{id}"), routerHandlers::getById)
      .andRoute(RequestPredicates.GET("/rest/employee/{id}/events"), routerHandlers::getEvents);
  }
}
