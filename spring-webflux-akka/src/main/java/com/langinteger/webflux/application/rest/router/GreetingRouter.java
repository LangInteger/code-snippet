package com.langinteger.webflux.application.rest.router;

import com.langinteger.webflux.application.rest.handler.GreetingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GreetingRouter {

  private static final Logger LOG = LoggerFactory.getLogger(GreetingRouter.class);

  @Bean
  public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
    LOG.debug("Initialize route to /hello");
    return RouterFunctions
        .route(RequestPredicates.GET("/helloWithActor").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), greetingHandler::helloWithActor)
        .andRoute(RequestPredicates.GET("/helloWorld").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), greetingHandler::helloWorld)
        .andRoute(RequestPredicates.GET("/helloWithStream").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), greetingHandler::helloWithStream);
  }
}