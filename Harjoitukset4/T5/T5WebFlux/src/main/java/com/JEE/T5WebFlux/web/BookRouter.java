package com.JEE.T5WebFlux.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
@Configuration
public class BookRouter {
    @SuppressWarnings("deprecation")
	@Bean
    public RouterFunction<ServerResponse> route(BookHandler handler) {
        return RouterFunctions
                .route(GET("/fbooks").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(GET("/fbook/{id}").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::findById)
                .andRoute(POST("/fbook").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(DELETE("/fbook/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }
}