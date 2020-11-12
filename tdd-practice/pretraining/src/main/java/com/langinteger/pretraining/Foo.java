package com.langinteger.pretraining;

public class Foo {

  public static void main(String[] args) {
    print();
    printWithFeatureOne();
    printWithFeatureTwo();
    printWithFeatureThree();
  }

  private static void printWithFeatureThree() {
    for (int index = 1; index < 100; index++) {
      if (index % 3 == 0 && index % 5 == 0) {
        System.out.println("FizzBuzz");
      } else {
        System.out.println(index);
      }
    }
  }

  private static void printWithFeatureTwo() {
    for (int index = 1; index < 100; index++) {
      if (index % 5 == 0) {
        System.out.println("Buzz");
      } else {
        System.out.println(index);
      }
    }
  }

  private static void printWithFeatureOne() {
    for (int index = 1; index < 100; index++) {
      if (index % 3 == 0) {
        System.out.println("Fizz");
      } else {
        System.out.println(index);
      }
    }
  }

  private static void print() {
    for (int index = 1; index < 100; index++) {
      System.out.println(index);
    }
  }

}
