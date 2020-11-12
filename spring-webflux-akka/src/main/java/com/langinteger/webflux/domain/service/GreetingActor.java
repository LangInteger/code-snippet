package com.langinteger.webflux.domain.service;

import akka.actor.AbstractActor;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends AbstractActor {

  private GreetingService greetingService;

  /**
   * DI
   */
  public GreetingActor(GreetingService greetingService) {
    this.greetingService = greetingService;
  }

//  @Override
//  public void onReceive(Object message) {
//    if (message instanceof Greet) {
//      String name = ((Greet) message).getName();
//      getSender().tell(greetingService.greet(name), getSelf());
//    } else {
//      unhandled(message);
//    }
//  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(Greet.class, name -> greetingService.greet(name.getName()))
        .build();
  }

  /**
   * GreetingActor 接受的消息对象
   */
  @Data
  public static class Greet {
    private String name;

    /**
     * 创建对象的方法
     *
     * @param name 姓名
     * @return Greet 对象
     */
    public static Greet create(String name) {
      Greet ret = new Greet();
      ret.name = name;
      return ret;
    }
  }
}
