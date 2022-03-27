package com.sofkau.libraryReactive.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class CreateResourceRouter {
    @Bean
    public RouterFunction<ServerResponse> createResource()
}
