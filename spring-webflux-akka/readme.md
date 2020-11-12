# spring-webflux 与 akka 集成的 demo

## 1. spring boot 与 akka 集成

Akka 是一套根据 Actor 并发模型，基于 scala 语言实现的框架。spring boot 与 akka 集成主要考虑到 actor 无法使用构造器实例化，借助 AKKA 的 `akka.actor.AbstractExtensionId` 的扩展来解决这一问题。

## 2. reactor 与 akka 集成

Akka 不希望将 Reactive Streams 定义的相关接口如 Subscriber、Publisher 等直接暴露出来，而想让用户使用其在内部使用这些接口同时抽象出来的 Source、Sink、Flow 等。具体原因为：


> The fact of Reactive Streams being an SPI, and as such is hard to “get right” in ad-hoc implementations. Thus Akka Streams discourages the use of the hard to implement pieces of the underlying infrastructure, and offers simpler, more type-safe, yet more powerful abstractions for users to work with: GraphStages and operators.


关于 Akka 和其它 Reactive Streams implementations（如 spring webflux 依赖的 reactor 项目） 之间的互通，akka 提供了一种方式，主要为：

> It is of course still (and easily) possible to accept or obtain Reactive Streams (or JDK+ Flow) representations of the stream operators by using methods like asPublisher or fromSubscriber.

上述资料可以在：https://doc.akka.io/docs/akka/current/general/stream/stream-design.html 找到。将 Akka 和其它 Reactive Streams implementations (such as Spring-Webflux) 结合使用的[办法](https://doc.akka.io/docs/akka/current/general/stream/stream-design.html#interoperation-with-other-reactive-streams-implementations)

```java
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Source;
import akka.stream.scaladsl.Sink;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class GreetingHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GreetingHandler.class);

  public Mono<ServerResponse> hello(ServerRequest request) {
    LOG.debug("Enter hello method with mono returned");

    ActorSystem system = ActorSystem.create("system");
    ActorMaterializer materializer = ActorMaterializer.create(system);

    Publisher<String> stringPublisher = Source
        .from(Arrays.asList("Hello", "World", "!"))
        .runWith(Sink.asPublisher(true), materializer);

    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
        .body(stringPublisher, String.class);
  }
}
```

## 3 Difference between UntypedActor and AbstractActor

From [Akka Migration Guide 2.4.x to 2.5.x](https://doc.akka.io/docs/akka/current/project/migration-guide-2.4.x-2.5.x.html), compared to `UntypedActor`, `AbstractActor`:

- It gives a clear entry point of what to implement. The compiler tells you that the abstract method must be implemented.
- It’s impossible to forget to set the receive behavior.
- It’s not possible to define the receive behavior more than once.

## 4. Reference

- [`Baeldung`: Introduction to Spring with Akka](https://www.baeldung.com/akka-with-spring)
- [`SO Thread`: Solution for “UntypedActor” deprecated in Akka (Java) Tutorial](https://stackoverflow.com/questions/20181516/solution-for-untypedactor-deprecated-in-akka-java-tutorial)