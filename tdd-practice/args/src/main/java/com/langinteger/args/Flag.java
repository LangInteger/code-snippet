package com.langinteger.args;

public class Flag {
  private String name;
  private String type;
  private String defaultValue;

  public Flag(String name, String type, String defaultValue) {
    this.name = name;
    this.type = type;
    this.defaultValue = defaultValue;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getDefaultValue() {
    return defaultValue;
  }
}
