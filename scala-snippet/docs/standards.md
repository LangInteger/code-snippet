# 代码标准

## 1 注释

scala 中的注释和 java 中基本一致：

- 多行注释以 /* 开头，以 */ 结尾
- 单行注释以 // 开头，并继续到行尾巴
- 文档注释是以 /** 开头，**/ 结尾，中间每行以 * 开头

## 2 Null

`Null` is the type of null in Scala, which is the same to null in java.

Although nulls are common in java, they are considered very bad practice in Scala. Null values cannot be checked by the compiler, leading to possible runtime erros in the form of `NullPointerException`. 
