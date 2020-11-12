package com.langinteger.args;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

  private Schema schema;

  public Parser(Schema schema) {
    this.schema = schema;
  }

  public String[][] parse(String args) {
    List<String> flagWithValues = Arrays.stream(args.split("-")).filter(item -> !item.isBlank()).collect(Collectors.toList());
    schema.checkFlagCount(flagWithValues);

    String[][] result = new String[flagWithValues.size()][];
    int index = 0;
    for (String item : flagWithValues) {
      String[] flagAndValue = item.split(" ");
      String flag = flagAndValue[0];
      String value = flagAndValue.length == 1 ? "" : flagAndValue[1];

      if (schema.isValueCompatibleWithFlag(flag, value)) {
        String curResult = schema.calculateValueWithDefault(flag, value);
        result[index] = new String[]{flag, curResult};
      } else {
        throw new RuntimeException(String.format("值：%s 与标记：%s 类型不匹配", value, false));
      }
      index++;
    }

    return result;
  }
}
