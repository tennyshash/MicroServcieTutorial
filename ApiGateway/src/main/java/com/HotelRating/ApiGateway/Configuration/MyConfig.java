package com.HotelRating.ApiGateway.Configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("USER-SERVICE",   r-> r.path("/users/**")  .uri("lb://USER-SERVICE"))
                .route("HOTEL-SERVICE",  r-> r.path("hotels/**" ) .uri("lb://HOTEL-SERVICE"))
                .route("HOTEL-SERVICE",  r-> r.path(("staff/**"))    .uri("lb://HOTEL-SERVICE"))
                .route("RATING-SERVICE", r-> r.path("/ratings/**").uri("lb://RATING-SERVICE"))
                .build();
    }
}
