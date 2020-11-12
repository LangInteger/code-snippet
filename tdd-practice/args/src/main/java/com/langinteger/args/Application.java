package com.langinteger.args;

import java.util.Scanner;

public class Application {

  public static void main(String[] args) {
    Schema schema = new Schema();
    Parser parser = new Parser(schema);
    Scanner sc = new Scanner(System.in);

    System.out.println("请指定命令行参数个数：");
    int count = Integer.parseInt(sc.nextLine());
    schema.setFlagCount(count);

    while (true) {
      System.out.println("请指定标记，例如：p int 0，依次为标记名、标记类型、标记默认值。输入 end 表示输入结束");

      String plainFlag = sc.nextLine();
      if (plainFlag.equals("end")) {
        if (schema.getFlags().size() != count) {
          throw new RuntimeException("输入的标记个数需为：" + count);
        }
        break;
      }

      String[] data = plainFlag.split(" ");
      if (data.length != 3) {
        throw new RuntimeException("输入有误，请重新输入");
      }

      schema.addSchemaFlagAndType(data[0], data[1], data[2]);
    }

    System.out.println("请指定要解析的参数字符串：");
    String arguments = sc.next();
    String[][] result = parser.parse(arguments);
    StringBuilder builder = new StringBuilder();
    for (String[] strings : result) {
      builder.append(strings[0]).append(": ").append(strings[1]).append("\\n");
    }
    System.out.println("解析结果为：" + builder);

  }
}
