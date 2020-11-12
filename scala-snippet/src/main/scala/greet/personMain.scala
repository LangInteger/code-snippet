object personMain extends App {

    // person 为一 object
    person.greet(person)

    // Person 有构造方法，写在类名同行上
    val p1 = new Person("Angela", "Baby")
    p1.greet

    // Person 有构造方法，写在类名同行上
    val p2 = new Person("Nao", "Taitao")
    p2.greet

    // PersonWithoutConstructor 没有构造方法，将 field 定义在类名上即得到一个免费的构造方法
    val p3 = new PersonWithoutConstructor("GU", "TingFang")
    p3.greet

    // 方法参数有默认值，故此处可以不传
    PersonWithKeywordParameter.greet("FirstNameByOrder")

    // 指定参数名赋值，可以避免因为参数顺序带来灾祸
    PersonWithKeywordParameter.greet(lastName = "LastNameSpecified")
}