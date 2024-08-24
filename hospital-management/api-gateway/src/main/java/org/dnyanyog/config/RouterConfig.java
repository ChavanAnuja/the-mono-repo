package org.dnyanyog.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder
        .routes()
        // directory service
        .route(
            "directory_route",
            r -> r.path("/api/v1/directory/**").uri("http://directory-service:8081"))
        .route(
            "login_route",
            r -> r.path("/api/v1/directory/validate").uri("http://directory-service:8081"))
        .route("add_user", r -> r.path("/api/v1/directory/**").uri("http://directory-service:8081"))

        // patient service
        .route(
            "patient_route", r -> r.path("/api/v1/patient/**").uri("http://patient-service:8082"))

        // case service
        .route("case_route", r -> r.path("/api/v1/case/**").uri("http://case-service:8083"))
        .route(
            "case_update_route",
            r -> r.path("/api/v1/case/update/**").uri("http://case-service:8083"))
        .route("case_get_route", r -> r.path("/api/v1/case/get/**").uri("http://case-service:8083"))

        // appointment service
        .route(
            "appointment_route",
            r -> r.path("/api/v1/appointment/**").uri("http://appointment-service:8084"))
        .route(
            "appointment_get_route",
            r -> r.path("/api/v1/appointment/get/**").uri("http://appointment-service:8084"))
        .route(
            "appointment_update_route",
            r -> r.path("/api/v1/updateAppointment/**").uri("http://appointment-service:8084"))
        .build();
  }
}
