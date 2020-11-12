package com.langinteger.args;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

public class SchemaTest {

  @ParameterizedTest
  @CsvSource({
      "1, false",
      "0, true",
      "-1, true"
  })
  void testSetSchemaFlagCount(int count, boolean hasException) {
    Schema schema = new Schema();
    try {
      schema.setFlagCount(count);
      if (hasException) {
        throw new AssertionError("未抛出异常");
      }
    } catch (RuntimeException ex) {
      if (hasException) {
        Assertions.assertThat(ex.getMessage().contains("标记数量必须大于0"));
      }
    }
  }

  @ParameterizedTest
  @CsvSource({
      "1, 1, true",
      "1, 2, false"
  })
  void testFlagCountChecker(int flagNumberSchemaSpecified, int flagNumberFlagWithValuesHas, boolean result) {
    Schema schema = new Schema();
    schema.setFlagCount(flagNumberSchemaSpecified);
    List<String> flagWithValues = new ArrayList<>();
    for (int i = 0; i < flagNumberFlagWithValuesHas; i++) {
      flagWithValues.add("xx");
    }
    Assertions.assertThat(schema.checkFlagCount(flagWithValues)).isEqualTo(result);
  }

  @ParameterizedTest
  @CsvSource({
      "'a', 'string', '', false, 'nothing', 1",
      "'b', 'boolean', 'true', false, 'nothing', 1",
      "'c', 'int', '0', false, 'nothing', 1",
      "'c', 'int', 'xx', false, '默认值：xx 与类型：int 不匹配', 1",
      "'d', 'long', 'xx', true, '不支持的标记类型：long', 0",
      "'dd', 'int', 'xx', true, '不支持的标记名称：dd', 0"
  })
  void testAddSchemaFlagAndType(String flag, String type, String defaultValue, boolean hasException, String message, int flagSize) {
    Schema schema = new Schema();
    try {
      schema.addSchemaFlagAndType(flag, type, defaultValue);
      if (hasException) {
        throw new AssertionError("未抛出异常");
      }
      Assertions.assertThat(schema.getFlags().size()).isEqualTo(flagSize);
      Assertions.assertThat(schema.getFlags().get(0).getDefaultValue()).isEqualTo(defaultValue);
    } catch (RuntimeException ex) {
      if (hasException) {
        Assertions.assertThat(ex.getMessage().contains(message));
      }
    }
  }

  @ParameterizedTest
  @CsvSource({
      "'a', '1', true",
      "'a', '', true",
      "'a', 's', false"
  })
  void testSchemaFlagAndValueMatched(String flag, String value, boolean result) {
    Schema schema = new Schema();
    schema.addSchemaFlagAndType("a", "int", "0");

    Assertions.assertThat(schema.isValueCompatibleWithFlag(flag, value)).isEqualTo(result);
  }

  @ParameterizedTest
  @CsvSource({
      "'a', '1', '1'",
      "'a', '', '0'"
  })
  void testCalculateValueWithDefault(String flag, String value, String result) {
    Schema schema = new Schema();
    schema.addSchemaFlagAndType("a", "int", "0");

    Assertions.assertThat(schema.calculateValueWithDefault(flag, value)).isEqualTo(result);
  }

  @ParameterizedTest
  @CsvSource({
      "1, 'a', 'int', '0', '-a 100', '100'",
      "1, 'a', 'int', '0', '-a', '0'"
  })
  void testParse(int flagCount, String flagName, String type, String defaultValue, String args, String parsedValue) {
    Schema schema = new Schema();
    schema.setFlagCount(flagCount);
    schema.addSchemaFlagAndType(flagName, type, defaultValue);

    Parser parser = new Parser(schema);
    String[][] result = parser.parse(args);

    Assertions.assertThat(result[0][1]).isEqualTo(parsedValue);
  }

}
