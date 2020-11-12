object CatMain extends App {
    println(Cat1.name)

    // Cat 类抽象了猫的属性，是创建猫对象的模板
    val cat1 = new Cat("Oswald", "Black", "Milk")
    println(cat1)
    val cat2 = new Cat("Henderson", "Ginger", "Chips")
    println(cat2)
    val cat3 = new Cat("Quentin", "Tabby and white", "Curry")
    println(cat3)

    // 判断此猫是不是喜欢 Chip
    println(ChipShop.willServe(cat1))
    println(ChipShop.willServe(cat2))
}