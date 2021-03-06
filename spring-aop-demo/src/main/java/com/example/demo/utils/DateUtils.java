package com.example.demo.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

/**
 * 日期工具类.
 * 采用joda工具包实现.
 */
@Validated
@SuppressWarnings("PMD.GodClass")
public final class DateUtils {

  public static final String SHORT_DATE_TIME_SPLITTER = " ";
  private static final int ONE = 1;
  private static final String INVALID_DATE_TIME_PATTERN = "%s时间格式非法";
  private static final String WRONG_DATA_FORMAT_PATTERN = "日期参数: %s 格式不正确";
  private static final String WRONG_TIME_FORMAT_PATTERN = "时间参数: %s 格式不正确";

  private static final long SECOND_IN_ONE_MINUTE = 60L;

  private static final long MILLISECOND_IN_ONE_SECOND = 1000L;

  /**
   * 所有服务的统一格式
   */
  public static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(PATTERN);

  private static final DateTimeFormatter SHORT_SHAPE_PATTERN = DateTimeFormat.forPattern("yyyyMMdd");
  private static final DateTimeFormatter SIMPLE_DATE_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd");
  public static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
  private static final DateTimeFormatter DATE_TIME_PATTERN_WITH_SECOND = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

  private static final DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm");
  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm:ss");

  /**
   * 验证简单日期格式（yyyy-MM-dd）的正则表达式
   */
  public static final String SIMPLE_DATE_REGEX = "(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-"
      + "(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}"
      + "(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)";

  /**
   * 验证简单日期格式（yyyy-MM-dd）的正则表达式
   */
  public static final String SIMPLE_DATE_OR_NULL_REGEX = "(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-"
      + "(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}"
      + "(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)|";

  /**
   * 验证UTC时间格式的正则表达式
   */
  public static final String ISO_DATE_REGEX = "^((((19|20)\\d{2})-(0?[13-9]|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)"
      + "|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))T"
      + "(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.[0-9]{3})Z";

  /**
   * 北京/上海时间
   */
  public static final String ZONE_SHANGHAI = "+08:00";

  private DateUtils() {
    // do nothing
  }

  /**
   * get current date time in string.
   *
   * @return
   */
  public static String now() {
    return DateTime.now().toInstant().toString(DATE_TIME_FORMATTER);
  }

  /**
   * 获取UTC时间，并且减去指定分钟数
   *
   * @param offsetMinutes 要减去的分钟数
   * @return String
   */
  public static String getUtcNowWithMinuteMinus(int offsetMinutes) {
    Instant instant = DateTime.now().toInstant();

    Instant newInstance = instant.minus(offsetMinutes * SECOND_IN_ONE_MINUTE * MILLISECOND_IN_ONE_SECOND);
    return newInstance.toString(DATE_TIME_FORMATTER);
  }

  /**
   * 获取UTC时间，并且加上指定分钟数
   * <p>
   * 实际上与上面getUtcNowWithMinuteMinus方法有所重复，但是不想通过计算来实现时间的加减
   *
   * @param offsetMinutes 要加上的分钟数
   * @return String
   */
  public static String getUtcNowWithMinutePlus(int offsetMinutes) {
    Instant instant = DateTime.now().toInstant();

    Instant newInstance = instant.plus(offsetMinutes * SECOND_IN_ONE_MINUTE * MILLISECOND_IN_ONE_SECOND);
    return newInstance.toString(DATE_TIME_FORMATTER);
  }

  /**
   * 获取UTC时间，并且位移指定秒数
   *
   * @param offsetSecond 要位移的秒数
   * @return String
   */
  public static String getUtcNowWithSecondMinus(int offsetSecond) {
    Instant instant = DateTime.now().toInstant();

    Instant newInstance = instant.minus(offsetSecond * MILLISECOND_IN_ONE_SECOND);
    return newInstance.toString(DATE_TIME_FORMATTER);
  }

  /**
   * 获取UTC时间，并且位移指定秒数
   *
   * @param offsetSecond 要位移的秒数
   * @return String
   */
  public static String getUtcNowWithSecondPlus(int offsetSecond) {
    Instant instant = DateTime.now().toInstant();

    Instant newInstance = instant.plus(offsetSecond * MILLISECOND_IN_ONE_SECOND);
    return newInstance.toString(DATE_TIME_FORMATTER);
  }

  /**
   * 获取北京时间，并且位移指定分钟
   *
   * @param offsetMinutes 要位移的分钟数
   * @return String
   */
  public static String getBeijingTimeWithMinutesPlus(int offsetMinutes) {
    Instant instant = DateTime.now().toInstant();

    Instant newInstance = instant.plus(offsetMinutes * SECOND_IN_ONE_MINUTE * MILLISECOND_IN_ONE_SECOND);
    return newInstance.toDateTime(DateTimeZone.forID(ZONE_SHANGHAI)).toString(DATE_TIME_PATTERN);
  }

  /**
   * get current date time in string with simple pattern.
   *
   * @return
   */
  public static String nowInSimplePattern() {
    return DateTime.now().toInstant().toString(SIMPLE_DATE_PATTERN);
  }

  /**
   * 获取北京时间的当前日期时间，格式为 yyyy-MM-dd'T'HH:mm:ss.SSS'Z' 的标准格式
   *
   * @return 当前日期时间（北京）
   */
  public static String nowOfBeijing() {
    return DateTime.now().toDateTime(DateTimeZone.forID(ZONE_SHANGHAI)).toString(PATTERN);
  }

  /**
   * 获取北京时间的当前日期，格式为 yyyy-MM-dd
   *
   * @return 当前日期（北京）
   */
  public static String nowDateOfBeijing() {
    return DateTime.now().toDateTime(DateTimeZone.forID(ZONE_SHANGHAI)).toString(SIMPLE_DATE_PATTERN);
  }

  /**
   * 获取北京时间的当前时间，格式为 HH:mm:ss
   *
   * @return 当前时间（北京）
   */
  public static String nowTimeOfBeijing() {
    return DateTime.now().toDateTime(DateTimeZone.forID(ZONE_SHANGHAI)).toString(TIME_FORMATTER);
  }

  /**
   * 获取北京时间的当前时间，格式为yyyy-MM-dd HH:mm:ss
   *
   * @return yyyy-MM-dd HH:mm:ss 格式的当前日期时间字符串
   */
  public static String nowDateTimeOfBeijingWithSeconds() {
    return DateTime.now().toDateTime(DateTimeZone.forID(ZONE_SHANGHAI)).toString(DATE_TIME_PATTERN_WITH_SECOND);
  }

  /**
   * 获取北京时间的当前时间，格式为yyyy-MM-dd HH:mm
   *
   * @return yyyy-MM-dd HH:mm 格式的当前日期时间字符串
   */
  public static String nowDateTimeOfBeijing() {
    return DateTime.now().toDateTime(DateTimeZone.forID(ZONE_SHANGHAI)).toString(DATE_TIME_PATTERN);
  }


  /**
   * 获取北京时间的当前时间，格式为 HH:mm
   *
   * @return 当前时间（北京）
   */
  public static String nowShortTimeOfBeijing() {
    return DateTime.now().toDateTime(DateTimeZone.forID(ZONE_SHANGHAI)).toString(SHORT_TIME_FORMATTER);
  }

  /**
   * 获取北京时间的当前日期，格式为 yyyy-MM-dd
   *
   * @return 当前日期（北京）
   */
  public static String nowInSimplePatternOfBeijing() {
    return DateTime.now().toDateTime(DateTimeZone.forID(ZONE_SHANGHAI)).toString(SIMPLE_DATE_PATTERN);
  }

  /**
   * get date time from string value.
   *
   * @param value
   * @return
   */
  public static DateTime from(String value) {
    return DateTime.parse(value, DATE_TIME_FORMATTER);
  }

  /**
   * parse a date time to local time
   *
   * @param value
   * @return
   */
  public static DateTime parse(String value) {
    return DateTime.parse(value);
  }

  /**
   * 转换日期字符串为 DateTime 对象
   *
   * @param value 日期字符串 yyyy-MM-dd
   * @return DateTime 对象
   */
  public static DateTime parseDate(String value) {
    return DateTime.parse(value, DATE_TIME_PATTERN);
  }

  /**
   * 转换 HH:ss 格式的字符串为 DateTime
   *
   * @param startTime HH:ss 形式的时间字符串
   * @return 对应的 DateTime 对象
   */
  public static DateTime parseTime(String startTime) {
    return DateTime.parse(startTime, DateUtils.SHORT_TIME_FORMATTER);
  }

  /**
   * parse a date time to beijing time
   *
   * @param value
   * @return
   */
  public static DateTime parseInBeijing(String value) {
    String value2 = StringUtils.replace(value, StringUtils.SPACE, "T");
    return new DateTime(value2, DateTimeZone.forID(ZONE_SHANGHAI));
  }

  /**
   * parse a date time to beijing date str
   *
   * @param value yyyy-MM-dd'T'HH:mm:ss.SSS'Z'格式的时间
   * @return 北京时间yyyy-MM-dd格式的日期字符串
   */
  public static String parseDateInBeijing(String value) {
    Assert.isTrue(StringUtils.isNotBlank(value) && value.matches(ISO_DATE_REGEX), String.format(INVALID_DATE_TIME_PATTERN, value));
    return parseInBeijing(value).toString(SIMPLE_DATE_PATTERN);
  }

  /**
   * parse a date time to utc time
   *
   * @param value
   * @return
   */
  public static DateTime parseInUtc(String value) {
    String value2 = StringUtils.replace(value, StringUtils.SPACE, "T");
    return new DateTime(value2, DateTimeZone.UTC);
  }

  /**
   * parse a string to local time
   *
   * @param value
   * @return
   */
  public static LocalDateTime parseLocalDateTime(String value) {
    String value2 = StringUtils.replace(value, StringUtils.SPACE, "T");
    return LocalDateTime.parse(value2);
  }

  /**
   * get today for beijing time
   *
   * @return
   */
  public static LocalDate getLocalDateInBeijing() {
    return LocalDate.now(DateTimeZone.forID(ZONE_SHANGHAI));
  }

  /**
   * get Cst time now
   *
   * @return
   */
  public static DateTime getCstNow() {
    return new DateTime(DateTimeZone.forID(ZONE_SHANGHAI));
  }


  /**
   * 对简单形式的日期字符串格式进行检查
   *
   * @param simpleDateStr 理论上为形如yyyy-MM-dd的日期字符串，且应符合正常的日期规则（如闰年等）
   * @return 日期字符串是否满足要求
   */
  public static boolean checkSimpleDateStr(String simpleDateStr) {
    return simpleDateStr.matches(SIMPLE_DATE_REGEX);
  }

  /**
   * 获得形如"yyyyMMdd的当前日期"
   *
   * @return 当前日期
   */
  public static String getShortShapeDate() {
    return DateTime.now().toInstant().toString(SHORT_SHAPE_PATTERN);
  }

  /**
   * 转换日期时间对象为日期字符串
   *
   * @param dateTime 日期时间对象
   * @return 日期字符串，形式为 yyyy-MM-dd
   */
  public static String toDateString(DateTime dateTime) {
    return dateTime.toString(SIMPLE_DATE_PATTERN);
  }

  /**
   * 将UTC格式的UTC时间字符串转为FlightPattern
   */
  public static String fromUtcToBeijingInFlightPattern(String utc) {
    return DateTime.parse(utc).toDateTime(DateTimeZone.forID(ZONE_SHANGHAI)).toString(DATE_TIME_PATTERN);
  }

  /**
   * beijing -> utc
   *
   * @param beijing 支持 yyyy-MM-dd； yyyy-MM-dd HH:mm:ss； utc格式
   * @return
   */
  public static String fromBeijingToUtc(String beijing) {
    String objStringDateTime;

    // yyyy-MM-dd'T'HH:mm:ss 或者 yyyy-MM-dd （不支持纯时间）
    boolean isDateOrUtcTime = !beijing.contains(StringUtils.SPACE);
    if (isDateOrUtcTime) {
      objStringDateTime = beijing;
    } else {
      // 将 yyyy-MM-dd HH:mm:ss 转换为 yyyy-MM-dd'T'HH:mm:ss
      objStringDateTime = StringUtils.replace(beijing, StringUtils.SPACE, "T");
    }
    return new DateTime(objStringDateTime, DateTimeZone.forID(ZONE_SHANGHAI)).toDateTime(DateTimeZone.UTC).toString();
  }

  /**
   * 对传进来的日期yyyy-MM-dd进行day的加减操作
   *
   * @param date    源日期字符串
   * @param addDays 需要添加的天数
   * @return 经过天数添加之后的目标日期字符串
   */
  public static String addDaysForSimpleDate(String date, int addDays) {
    Assert.isTrue(date.matches(SIMPLE_DATE_REGEX), "输入的日期格式不正确");
    DateTime parsedDate = new DateTime(date, DateTimeZone.forID(ZONE_SHANGHAI));
    DateTime addedDate = parsedDate.plusDays(addDays);
    return addedDate.toString(SIMPLE_DATE_PATTERN);
  }

  /**
   * 将北京时间字符串，加上相应的天数，仍然返回北京时间
   *
   * @param beijingDate 源日期字符串
   * @param addDays     需要添加的天数
   * @return 经过天数添加之后的目标日期字符串
   */
  public static String addDaysFromBeijing(String beijingDate, int addDays) {
    String formattedDate = beijingDate;
    if (beijingDate.contains(StringUtils.SPACE)) {
      formattedDate = StringUtils.replace(beijingDate, StringUtils.SPACE, "T");
    }
    return new DateTime(formattedDate, DateTimeZone.forID(ZONE_SHANGHAI)).plusDays(addDays).toString(SIMPLE_DATE_PATTERN);
  }

  /**
   * 将北京时间字符串，减去指定月数，返回北京时间:
   * 如: 2019-08-01 - 2 months = 2019-06-01
   * <p>
   * 对于大月天数比小月多的情况,会返回小月的最后一天:
   * 2019-07-31 - 1 months = 2019-06-30
   *
   * @param beijingDate 源北京日期字符串 yyyy-MM-dd
   * @param minusMonths 需要减去的月数
   * @return 源日期减去指定月数后的北京日期字符串 yyyy-MM-dd
   */
  public static String minusMonthFromBeijing(String beijingDate, int minusMonths) {

    return new DateTime(beijingDate, DateTimeZone.forID(ZONE_SHANGHAI)).minusMonths(minusMonths).toString(SIMPLE_DATE_PATTERN);
  }

  /**
   * 对utc时间做减法，获得的utc时间
   *
   * @param utcTime     源时间
   * @param minusMonths 需要减少的月份
   * @return 减法计算后的utc时间
   */
  public static String minusMonthsForUtcTime(String utcTime, int minusMonths) {
    return DateTime.parse(utcTime, DATE_TIME_FORMATTER).minusMonths(minusMonths).toString(DATE_TIME_FORMATTER);
  }

  /**
   * 基于当前时间做减法后，获得的utc时间
   *
   * @param minusMonths 需要减少的月份
   * @return 减法计算后的utc时间
   */
  public static String getUtcTimeAfterMinusMonthsFromNow(int minusMonths) {
    return minusMonthsForUtcTime(DateUtils.now(), minusMonths);
  }

  /**
   * @param dateTime 日期时间字符串
   * @param hours    小时数
   * @return 减去指定小时后的日期时间字符串 yyyy-MM-dd HH:mm:ss
   */
  public static String minusHours(String dateTime, int hours) {
    String value = StringUtils.replace(dateTime, StringUtils.SPACE, "T");
    return new DateTime(value).minusHours(hours).toString(DATE_TIME_PATTERN_WITH_SECOND);
  }

  /**
   * 对utc时间做减法，减少指定小时数
   *
   * @param utcTime 源时间
   * @param hours   需要减少的小时数
   * @return 减法计算后的utc时间
   */
  public static String minusHoursForUtcTime(String utcTime, int hours) {
    return DateTime.parse(utcTime, DATE_TIME_FORMATTER).minusHours(hours).toString(DATE_TIME_FORMATTER);
  }

  /**
   * 当前时间减去指定小时数
   *
   * @param hours 需要减少的小时数
   * @return 当前时间减去指定小时数后的utc时间
   */
  public static String minusHoursOfNowForUtcTime(int hours) {
    return minusHoursForUtcTime(nowOfBeijing(), hours);
  }

  /**
   * 把yyyy-mm-dd MM:dd结构的时间数据中的yyyy-mm-dd返回
   *
   * @param dateTime
   * @return
   */
  public static String getStringDate(String dateTime) {
    String[] dateTimeArray = dateTime.split(" ");

    return dateTimeArray[0];
  }

  /**
   * 把yyyy-mm-dd MM:dd结构的时间数据中的MM:dd返回
   *
   * @param dateTime
   * @return
   */
  public static String getStringTime(String dateTime) {
    String[] dateTimeArray = dateTime.split(" ");

    return dateTimeArray[1];
  }

  /**
   * 北京时间DateTime
   *
   * @return
   */
  public static DateTime nowOfCst() {
    return DateTime.now(DateTimeZone.forID(ZONE_SHANGHAI));
  }

  /**
   * 计算两个日期之间的天数
   *
   * @return
   */
  public static int countDaysBetweenTwoDate(String beginDate, String endDate) {

    DateTime beginDateTime = DateTime.parse(beginDate, SIMPLE_DATE_PATTERN);
    DateTime endDateTime = DateTime.parse(endDate, SIMPLE_DATE_PATTERN);
    return Days.daysBetween(beginDateTime, endDateTime).getDays() + ONE;
  }

  /**
   * 计算两个时间之间，相差的分钟
   *
   * @param beginTime 开始时间，小的时间
   * @param endTime   结束时间，大的时间
   * @return
   */
  public static int countMinutesBetweenTwoUtc(String beginTime, String endTime) {
    DateTime beginDateTime = DateTime.parse(beginTime, DATE_TIME_FORMATTER);
    DateTime endDateTime = DateTime.parse(endTime, DATE_TIME_FORMATTER);
    return Minutes.minutesBetween(beginDateTime, endDateTime).getMinutes();
  }

  /**
   * 计算两个时间之间，相差的分钟
   *
   * @param beginTime 开始时间，小的时间
   * @param endTime   结束时间，大的时间
   * @return
   */
  public static int countSecondsBetweenTwoUtc(String beginTime, String endTime) {
    DateTime beginDateTime = DateTime.parse(beginTime, DATE_TIME_FORMATTER);
    DateTime endDateTime = DateTime.parse(endTime, DATE_TIME_FORMATTER);
    return Seconds.secondsBetween(beginDateTime, endDateTime).getSeconds();
  }

  /**
   * 计算本日剩余的分钟数
   *
   * @return 本日剩余分钟数
   */
  public static int countRestMinutesTodayOfZoneShangHai() {
    DateTimeZone targetZone = DateTimeZone.forID(ZONE_SHANGHAI);
    return countRestMinutesToday(targetZone);
  }

  /**
   * 计算指定时区的今日剩余时间
   *
   * @param targetZone 目标时区
   * @return 指定时区今日剩余分钟数
   */
  public static int countRestMinutesToday(DateTimeZone targetZone) {
    DateTime nextDayStart = new LocalDate(targetZone).plusDays(1).toDateTimeAtStartOfDay();
    DateTime now = new DateTime(targetZone);
    return Minutes.minutesBetween(now, nextDayStart).getMinutes();
  }

  /**
   * 比较两个字符串时间是不是一天，只要字符串的前面是yyyy-MM-dd格式即可，后面的时间不关心
   */
  public static boolean isSameDay(String time1, String time2) {
    final int dayLength = 10;
    String day1 = time1.substring(0, dayLength);
    String day2 = time2.substring(0, dayLength);
    return day1.equals(day2);
  }

  /**
   * 计算两个时间之间的分钟数
   * 若开始时间大于结束时间，返回负数
   *
   * @param startTimeStr 开始时间字符串 格式：HH:mm
   * @param endTimeStr   结束时间字符串 格式：HH:mm
   * @return 两个时间之间的分钟数
   */
  public static int countMinutesBetweenTwoTime(String startTimeStr, String endTimeStr) {
    DateTime startTime = DateTime.parse(startTimeStr, SHORT_TIME_FORMATTER);
    DateTime endTime = DateTime.parse(endTimeStr, SHORT_TIME_FORMATTER);

    return Minutes.minutesBetween(startTime, endTime).getMinutes();
  }

  /**
   * 计算两个时间之间的小时数
   * 若开始时间小于结束时间，返回负数
   *
   * @param startDateTime 开始日期时间字符串 格式：yyyy-MM-dd HH:mm
   * @param endDateTime   结束日期时间字符串 格式：yyyy-MM-dd HH:mm
   * @return 两个时间之间的小时数
   */
  public static int countHoursBetweenTwoDateTime(String startDateTime, String endDateTime) {

    DateTime start = DateTime.parse(startDateTime, DATE_TIME_PATTERN);
    DateTime end = DateTime.parse(endDateTime, DATE_TIME_PATTERN);

    return Hours.hoursBetween(start, end).getHours();
  }

  /**
   * 计算两个时间之间的分钟数
   * 若开始时间小于结束时间，返回负数
   *
   * @param startDateTime 开始日期时间字符串 格式：yyyy-MM-dd HH:mm
   * @param endDateTime   结束日期时间字符串 格式：yyyy-MM-dd HH:mm
   * @return 两个时间之间的分钟数
   */
  public static int countMinutesBetweenTwoDateTime(String startDateTime, String endDateTime) {

    DateTime start = DateTime.parse(startDateTime, DATE_TIME_PATTERN);
    DateTime end = DateTime.parse(endDateTime, DATE_TIME_PATTERN);

    return Minutes.minutesBetween(start, end).getMinutes();
  }

  /**
   * 查验是否第一个时间大于第二个时间
   * 两个时间字符串应有相同的格式，否则结果可能出错
   *
   * @param first  时间一
   * @param second 时间二
   * @return true 若时间一大于时间二
   */
  public static boolean isFirstGreater(String first, String second) {
    return first.compareTo(second) > 0;
  }

  /**
   * 查验是否第一个时间大于第二个时间
   *
   * @param firstDateTime  时间一 格式：yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
   * @param secondDateTime 时间二 格式：yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
   * @return true 若时间一大于时间二
   */
  public static boolean isFirstDateTimeGreater(String firstDateTime, String secondDateTime) {
    Assert.isTrue(StringUtils.isNotBlank(firstDateTime) && firstDateTime.matches(ISO_DATE_REGEX), String.format(WRONG_DATA_FORMAT_PATTERN, firstDateTime));
    Assert.isTrue(StringUtils.isNotBlank(secondDateTime) && secondDateTime.matches(ISO_DATE_REGEX), String.format(WRONG_DATA_FORMAT_PATTERN, secondDateTime));
    return firstDateTime.compareTo(secondDateTime) > 0;
  }

  public static boolean isDateTimeAfterNowInBeiJing(String dateTime) {
    String now = nowDateTimeOfBeijingWithSeconds();
    boolean isDateTimeAfterNowInBeiJing = dateTime.compareTo(now) >= 1;

    return isDateTimeAfterNowInBeiJing;
  }


  /**
   * 截取日期字符串
   *
   * @param dateTime 日期时间 只要字符串的前面是yyyy-MM-dd格式即可，后面的时间不关心
   * @return 日期
   */
  public static String getDateFromDateTime(String dateTime) {

    int dayLength = 10;
    return dateTime.substring(0, dayLength);
  }

  /**
   * 将utc格式时间转为 北京/上海时间
   *
   * @param utc 时间格式:yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
   * @return 北京/上海时间,格式:yyyy-MM-dd HH:mm
   */
  public static String fromUtcToDateTime(String utc) {
    DateTime dateTime = parseInUtc(utc).toDateTime(DateTimeZone.forID(ZONE_SHANGHAI));
    return dateTime.toString(DATE_TIME_PATTERN);
  }

  /**
   * 判断时间是否在某个区间内
   *
   * @param startDate 开始时间
   * @param endDate   结束时间
   * @param dateTime  要比对的时间
   * @return boolean
   */
  public static boolean isInSection(String startDate, String endDate, DateTime dateTime) {
    DateTime startTime = DateTime.parse(startDate, SIMPLE_DATE_PATTERN);
    DateTime endTime = DateTime.parse(endDate, SIMPLE_DATE_PATTERN);

    boolean isBefore = startTime.isBefore(dateTime);
    boolean isAfter = endTime.isAfter(dateTime);

    return isBefore && isAfter;
  }

  /**
   * utc 时间转为北京时间
   *
   * @param utc 时间格式:yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
   * @return 北京/上海时间,格式:yyyy-MM-dd
   */
  public static String fromUtcToBeijingInSimplePattern(String utc) {
    return DateTime.parse(utc).toDateTime(DateTimeZone.forID(ZONE_SHANGHAI)).toString(SIMPLE_DATE_PATTERN);
  }
}
