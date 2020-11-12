package com.langinteger.webflux.infrastructure.util;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import org.springframework.stereotype.Component;

@Component
public class AkkaUtils {

  private static ActorSystem actorSystem = null;

  /**
   * DI
   */
  public AkkaUtils(ActorSystem actorSystem) {
    this.actorSystem = actorSystem;
  }

  /**
   * 创建 Materializer
   *
   * @return Materializer
   */
  public static ActorMaterializer createMaterializer() {
    return ActorMaterializer.create(actorSystem);
  }

}
