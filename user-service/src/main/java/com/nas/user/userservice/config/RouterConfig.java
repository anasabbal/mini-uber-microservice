package com.nas.user.userservice.config;


import com.nas.user.userservice.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(UserHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/v1/auth/login"), handler::loginUser)
                .andRoute(RequestPredicates.POST("/v1/auth/register"), handler::createUser);
    }
}
