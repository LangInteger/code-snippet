package com.langinteger.fizzbuzz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FizzBuzzTest {

  @ParameterizedTest
  @CsvSource({
      "1, '1'",
      "3, 'Fizz'",
      "5, 'Buzz'",
      "15, 'FizzBuzz'",
      "13, 'Fizz'",
      "51, 'Fizz'",
      "52, 'Buzz'"})
  void test(int number, String content) {
    Assertions.assertThat(FizzBuzz.of(number)).isEqualTo(content);
  }

}
