# 实操问题

## 1. sbt run witout scala version specified

若在 `build.sbt` 中没有指定 `scalaVersion := "x.x.x"`，执行 run 命令可能会出现以下错误

```text
[error]         Note: Unresolved dependencies path:
```

在 `build.sbt` 中通过此指定了 scala 版本之后立刻就解决了。比较奇怪，明明不指定也有一个默认的，但是就是没用。

## 2 object 的类型

```scala
object foo{
} 
```

的类型为 `foo.type`。若某方法接受 object 类型的参数，则传入参数需写为。`foo.type`。代表 object foo 这个单例对象的类型，与 Int 等 Scala 内置类型不同，限制了程序的抽象能力，应该通过使用 class 来补足。

## 3 code navigation does not work

- code navigation does not work for the file '/path/to/your/file/ExtendedCounter.scala' because it doesn't belong to a build target.
