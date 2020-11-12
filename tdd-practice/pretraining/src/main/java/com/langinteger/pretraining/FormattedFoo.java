package com.langinteger.pretraining;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FormattedFoo {

  private static final DivideByValues predicate = new DivideByValues();

  public static void main(String[] args) {
    print(1, 100, List.of(101), "");
    print(1, 100, List.of(3), "Fizz");
    print(1, 100, List.of(5), "Buzz");
    print(1, 100, List.of(3, 5), "FizzBuzz");
  }

  private static void print(int start, int limit, List<Integer> values, String replacement) {

    List<Object> result = calculateResult(start, limit, values, replacement);
    printResult(result);
  }

  private static void printResult(List<Object> result) {
    System.out.println(result);
  }

  private static List<Object> calculateResult(int start, int limit, List<Integer> values, String replacement) {
    return Stream.iterate(start, number -> ++number).limit(limit)
        .map(number -> predicate.apply(number, values) ? replacement : number)
        .collect(Collectors.toList());
  }

  static class DivideByValues implements BiFunction<Integer, List<Integer>, Boolean> {
    public Boolean apply(Integer target, List<Integer> values) {
      return values.stream()
          .allMatch(value -> target % value == 0);
    }
  }

}
