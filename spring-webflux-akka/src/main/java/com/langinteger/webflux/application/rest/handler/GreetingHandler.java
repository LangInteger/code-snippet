package com.langinteger.webflux.application.rest.handler;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.stream.javadsl.Source;
import akka.stream.scaladsl.Sink;
import akka.util.Timeout;
import com.langinteger.webflux.domain.service.GreetingActor;
import com.langinteger.webflux.domain.service.GreetingService;
import com.langinteger.webflux.infrastructure.config.SpringExtension;
import com.langinteger.webflux.infrastructure.util.AkkaUtils;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
public class GreetingHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GreetingHandler.class);
  private ActorSystem actorSystem;
  private GreetingService greetingService;

  /**
   * DI
   */
  public GreetingHandler(ActorSystem actorSystem,
                         GreetingService greetingService) {
    this.actorSystem = actorSystem;
    this.greetingService = greetingService;
  }

  /**
   * Akka 和 Reactor 相关 API 集成
   *
   * @param request HTTP 请求
   * @return HelloWorld 字符串
   */
  public Mono<ServerResponse> helloWorld(ServerRequest request) {
    LOG.debug("Enter hello method with request: {}", request);

    Publisher<String> stringPublisher = Source
        .from(Arrays.asList("Hello", "World", "!"))
        .runWith(Sink.asPublisher(true), AkkaUtils.createMaterializer());

    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
        .body(stringPublisher, String.class);
  }

  /**
   * 调用具体 Actor 的例子
   * not work, I don't know why
   *
   * @param request HTTP 请求
   * @return Hello xxx 字符串
   */
  public Mono<ServerResponse> helloWithActor(ServerRequest request) {
    LOG.debug("Enter hello method with request: {}", request);

    ActorRef greeter = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("greetingActor"), "greeter" + System.currentTimeMillis());

    String name = request.queryParam("name").orElse("noBody");
    Timeout timeout = new Timeout(Duration.create(10, TimeUnit.SECONDS));
    Future<Object> future = Patterns.ask(greeter, GreetingActor.Greet.create(name), timeout);
    try {
      Object ret = Await.result(future, timeout.duration());
      return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
          .body(BodyInserters.fromObject(ret));
    } catch (Exception e) {
      LOG.warn("Greeter actor return result exceeding time limitation", e);
      throw new RuntimeException("超时");
    }
  }

  /**
   * 调用 Stream 执行的例子
   *
   * @param request HTTP 请求
   * @return Hello xxx 字符串
   */
  public Mono<ServerResponse> helloWithStream(ServerRequest request) {
    LOG.debug("Enter hello method with request: {}", request);

    String name = request.queryParam("name").orElse("noBody");

    Publisher<String> greetingPublisher = Source.single(name)
        .async()
        .map(val -> greetingService.greet(val))
        .runWith(Sink.asPublisher(true), AkkaUtils.createMaterializer());

    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
        .body(greetingPublisher, String.class);
  }
}