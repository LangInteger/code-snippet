package com.langinteger.pretraining;

public class FizzBuzz {

  public static String of(int input) {
    String result = "";

    if (input % 15 == 0) {
      result = "FizzBuzz";
    } else if (input % 3 == 0) {
      result = "Fizz";
    } else if (input % 5 == 0) {
      result = "Buzz";
    } else {
      result = result + input;
    }

    return result;
  }
}
