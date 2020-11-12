package com.langinteger.webflux.infrastructure.config;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.langinteger.webflux.infrastructure.config.SpringExtension.SPRING_EXTENSION_PROVIDER;

@Configuration
@ComponentScan
public class ActorConfiguration {

  @Autowired
  private ApplicationContext applicationContext;

  @Bean
  public ActorSystem actorSystem() {
    ActorSystem system = ActorSystem.create("akka-spring-demo");
    SPRING_EXTENSION_PROVIDER.get(system)
        .initialize(applicationContext);
    return system;
  }

  @Bean
  public Config akkaConfiguration() {
    return ConfigFactory.load();
  }
}