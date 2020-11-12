package com.langinteger.args;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Schema {

  private int flagCount;
  private List<Flag> flags = new ArrayList<>();

  public Schema() {

  }

  public List<Flag> getFlags() {
    return flags;
  }

  /**
   * 设置 Schema 的 Flag 数量
   *
   * @param count Flag 数量
   */
  public void setFlagCount(int count) {
    if (count <= 0) {
      throw new RuntimeException("标记数量必须大于0");
    }
    this.flagCount = count;
  }

  /**
   * 检查 Flag 数量
   *
   * @param flagWithValues 待检查目标
   * @return true 若检查通过
   */
  public boolean checkFlagCount(List<String> flagWithValues) {
    return flagCount == flagWithValues.size();
  }

  /**
   * 添加 Schema 中的 Flag
   *
   * @param name         Flag 名
   * @param type         Flag 类型
   * @param defaultValue 默认值
   */
  public void addSchemaFlagAndType(String name, String type, String defaultValue) {
    if (!name.matches("[a-z]{1}")) {
      throw new RuntimeException(String.format("不支持的标记名称：%s", name));
    }

    if (!type.matches("int|string|boolean")) {
      throw new RuntimeException(String.format("不支持的标记类型：%s", type));
    }

    if (!defaultValue.isBlank()) {
      if (!isValueCompatibleWithType(type, defaultValue)) {
        throw new RuntimeException(String.format("默认值：%s 与类型：%s 不匹配", defaultValue, type));
      }
    }

    Flag flag = new Flag(name, type, defaultValue);
    this.flags.add(flag);
  }

  private boolean isValueCompatibleWithType(String type, String value) {
    boolean isValueTypeOk = true;
    switch (type) {
      case "int":
        if (!value.equals("")) {
          try {
            Integer.parseInt(value);
          } catch (NumberFormatException ex) {
            isValueTypeOk = false;
          }
        }
        break;
      case "boolean":
        isValueTypeOk = true;
        break;
      case "string":
        isValueTypeOk = true;
        break;
      default:
        isValueTypeOk = false;
        break;
    }
    return isValueTypeOk;
  }

  /**
   * 检查标记的值是否满足要求
   *
   * @param flag  标记名
   * @param value 标记值
   * @return true 若满足要求
   */
  public boolean isValueCompatibleWithFlag(String flag, String value) {
    Flag matchedFlag = getMatchFlag(flag);
    return isValueCompatibleWithType(matchedFlag.getType(), value);
  }

  /**
   * 计算标记的值，若未指定值则返回默认值
   *
   * @param flag  标记名
   * @param value 指定值
   * @return 标记值
   */
  public String calculateValueWithDefault(String flag, String value) {
    Flag matchedFlag = getMatchFlag(flag);
    if (value.equals("") && !matchedFlag.getDefaultValue().isBlank()) {
      return matchedFlag.getDefaultValue();
    } else {
      return value;
    }
  }

  private Flag getMatchFlag(String flag) {

    List<Flag> result = this.flags.stream()
        .filter(innerFlag -> innerFlag.getName().equals(flag))
        .collect(Collectors.toList());

    if (result.isEmpty()) {
      throw new RuntimeException(String.format("不支持的标记：%s", flag));
    }

    return result.get(0);
  }
}
