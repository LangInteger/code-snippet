package com.langinteger.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class GreetingWebClient {

  private static final Logger LOG = LoggerFactory.getLogger(GreetingWebClient.class);

  private WebClient client = WebClient.create("http://localhost:8080");

  private Mono<ClientResponse> result = client.get()
      .uri("/hello")
      .accept(MediaType.TEXT_PLAIN)
      .exchange();

  public String getResult() {
    LOG.debug("Get result in greeting web client");
    return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
  }
}