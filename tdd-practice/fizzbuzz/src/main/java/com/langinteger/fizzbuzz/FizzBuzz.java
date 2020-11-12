package com.langinteger.fizzbuzz;

public class FizzBuzz {

  public static String of(int number) {

    if (number % 15 == 0) {
      return "FizzBuzz";
    }

    if (number % 3 == 0 || String.valueOf(number).contains("3")) {
      return "Fizz";
    }

    if (number % 5 == 0 || String.valueOf(number).contains("5")) {
      return "Buzz";
    }

    return "" + number;
  }

  public static void main(String[] args) {
    for (int index = 1; index < 100; index++) {
      System.out.println(of(index));
    }
  }
}
