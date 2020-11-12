# 名词定义

## 1 Declarition versus Expression

Declaritions are different to expressions, they

- do not evaluate to a value 
- do not have a type

## 2 Method versus Field

- The body expression of a field is run only once after which the final value is stored in the object. 
- The body of a method, on the other hand, is evaluated every time we call the method.`

## 3 Object literals

When we write object literals we use declaration. A declaration does not evaluate to a value, instead, it associates a name with a value.

```scala
object foo{}
```

This is not an expression, it does not evaluate to a value. Rather, it binds a name (foo) to a value (an empty object).

## 4 Value of method

Method cannot be assigned to a field, so it's not an expression.

```scala
object calculator {
    def square(x: Int): Int = x * x 
}
val someField = calculator.square
```

This will lead to compile errors. 

When it comes to methods without arguments, it seems behave differently. However, it is just a trick of the syntax.

References to fields and calls to argumentless methods look identical in Scala. This is by design, to allow us to swap the implementation of a field for a method (and vice versa) without affecting other code. It is a programming language feature called the uniform access principle.

In summary, `calls to methods` are expressions but methods themselves are not expressions.

## 5 Class

A class is a template for creating objects that have similar methods and fields. In Scala, class also defines a type, and objects created from a class share the same type.

Like a object declaration, a class declaration binds a name and is not an expression. A class is not a value, and there is a different namespace in which classes live.

## 6 Functions

Functions are objects that can be invoked like methods.

### 6.1 The apply methodd

In Scala, by convention, an object can be called like a function if it has a method called apply.
