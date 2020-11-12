# Spring Boot With Jooq

为在 Spring Boot 中集成 Jooq，查阅了一些资料。大部分是以 maven 作为构建工具，由于日常使用 gradle，特留下这个例子作为记录。

## 1 配置 Jooq

在 Spring boot 基础上，还需要做如下配置集成 Jooq

### 1.1 引入 `nu.studer.jooq` 插件

```text
In `build.gradle`

plugins {
    id 'nu.studer.jooq' version '3.0.3'
}
```

[gradle-jooq-plugin 项目地址](https://github.com/etiennestuder/gradle-jooq-plugin)

### 1.2 定义 code generation task

```text
In `build.gradle`

apply from: "build_scripts/code_generation.gradle"
```

```text
In `code_generation.gradle`

dependencies {
    //生成代码时需要连接数据库
    jooqRuntime('mysql:mysql-connector-java')
}

jooq {
    //jooq 版本
    version = '3.11.11'
    edition = 'OSS'
    
    //Gradle task 定义，会得到一个名如 generate[ConfigurationName]JooqSchemaSource 的任务
    sample(sourceSets.main) {
        //数据库连接配置
        jdbc {
            driver = 'com.mysql.jdbc.Driver'
            url = 'jdbc:mysql://localhost:3306/jooq'
            user = 'root'
            password = ''
            schema = 'jooq'
        }
        //Jooq 代码生成器配置
        generator {
            name = 'org.jooq.codegen.DefaultGenerator'
            strategy {
                name = 'org.jooq.codegen.DefaultGeneratorStrategy'
            }
            database {
                name = 'org.jooq.meta.mysql.MySQLDatabase'
                inputSchema = 'jooq'
            }
            generate {
                daos = true
                relations = true
                deprecated = false
                records = true
                immutablePojos = true
                fluentSetters = true
                // ...
            }
            //生成代码的目标地址配置
            target {
                packageName = 'test.generated'
                directory = 'src/main/java'
            }
        }
    }
}

```

### 1.3 为项目引入 Jooq 相关依赖

```text
In `build.gradle`

dependencies {
    // jooq
    implementation 'org.springframework.boot:spring-boot-starter-jooq'
    implementation group: 'org.jooq', name: 'jooq', version: '3.11.11'
    implementation group: 'org.jooq', name: 'jooq-meta', version: '3.11.11'
    implementation group: 'org.jooq', name: 'jooq-codegen', version: '3.11.11'

    // for Java 11 and jOOQ 3.11.11 or before
    // JDK 11 中抛掉了一些代码，需要单独引入这些库
    implementation group: 'javax.xml.bind', name: 'jaxb-api', version: '2.3.1'
    implementation group: 'javax.activation', name: 'javax.activation-api', version: '1.2.0'
    implementation group: 'javax.annotation', name: 'javax.annotation-api', version: '1.3.2'
}
```

参考资料：

- [Java 11 migration guide](https://blog.codefx.org/java/java-11-migration-guide/)
- [SO thread: Replacements for deprecated JPMS modules with Java EE APIs](https://stackoverflow.com/questions/48204141/replacements-for-deprecated-jpms-modules-with-java-ee-apis/48204154#48204154)

## 2 一些坑

- [jooq 包 jooq-meta and jooq-codegen 重命名](https://github.com/jOOQ/jOOQ/issues/7419)
  - `org.jooq.codegen.DefaultGenerator` not `org.jooq.util.DefaultGenerator`
  - `org.jooq.codegen.DefaultGeneratorStrategy` not `org.jooq.util.DefaultGeneratorStrategy`
  - `org.jooq.meta.mysql.MySQLDatabase` not `org.jooq.util.mysql.MySQLDatabase`

- Execution failed for task ':compileJava'. java.lang.OutOfMemoryError: Java heap space
  - [来自 stackOverFlow 的解答 ](https://stackoverflow.com/questions/17447410/outofmemoryerror-when-compiling-my-android-app-with-gradle)

## 3 主要工具及框架

- Gradle 5.4.1
- Spring Boot 2.1.5
- IntelliJ IDEA 2019.1.2

## 4 参考资料

- [Spring Boot Support for jOOQ](https://www.baeldung.com/spring-boot-support-for-jooq)
- [Introduction to jOOQ with Spring](https://www.baeldung.com/jooq-with-spring)