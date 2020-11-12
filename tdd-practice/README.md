# TDD-Practice

## Part 1 Pre training

任务要求为：

```text
1.打印出从1到100的数字，

2.将其中3的倍数替换成“Fizz”，

3.5的倍数替换成“Buzz”，

4.既能被3整除、又能被5整除的数则替换成“FizzBuzz”。
```

看起来很简单的问题，花费的时间如下

- 初次解答在，大概 10 min
  - 主要代码 Foo.class
  - 非常的流水的代码
- 自我重构，消除了重复代码，但是仍未添加测试，大概 30 min
  - 主要代码 FormattedFoo.class
  - 总体来说非常糟糕，属于那种写代码思路不明确，摸不着头脑的水平
  - 接下来参考视频中 1min50s 完成这道题目的优秀程序员基于 TDD 的写法
- 参考大神编写代码的视频
  - 首先定义测试代码
    - 使用 Junit5 的 @ParameterizedTest 定义测试
    - 使用 @CsvSource 在注解中嵌入测试数据
    - 解决 Junit5 和 mvn 命令的集成问题 [Surefire is not picking up Junit 5 tests](https://stackoverflow.com/questions/36970384/surefire-is-not-picking-up-junit-5-tests)

## Part 2 FizzBuzz

一开始理解错了，其实 pre-training 中的问题即是 FizzBuzz 问题。重新考虑后解决问题的步骤为：

- 首先编写需要的方法的单元测试用例
- 根据单元测试用例建立需要的类及方法
- 跑单元测试用例体验失败的感觉
- 实现需要的方法
- 跑单元测试进行验证
- 循环上两步直至所有的测试用例通过
- 当有新需求时遵循上述三个步骤

通过解决此问题总结出来的技能要求：

- IDE 的快捷键操作
- 用 JUnit 编写单元测试
- 编写失败的测试，驱动出产品代码
- 充分利用代码生成
- 刻意练习的节奏

原则总结：

- 没有失败的测试就不能写代码
- 只写恰好让测试通过的代码

IDEA 快捷键总结：

- [IDEA 快捷键地址](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
- [MAC 快捷键及其图标](https://blog.csdn.net/yue31313/article/details/62049775)
- command + n 创建文件
- control + alt + r 运行

经过一段时间的尝试，解题时间被缩短到 150s 以内

## Part 3 Args

## Part 4 Mars Rover