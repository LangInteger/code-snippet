package com.langinteger.webflux.domain.service;

import org.springframework.stereotype.Component;

@Component
public class GreetingService {

  private static final String GREETING_PATTERN = "Hello, %s";

  /**
   * 创建问候语
   *
   * @param name 姓名
   * @return 问候语
   */
  public String greet(String name) {
    return String.format(GREETING_PATTERN, name);
  }
}
