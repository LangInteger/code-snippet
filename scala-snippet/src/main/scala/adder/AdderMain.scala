package adder

object AdderMain extends App {
    def adder = new Adder(1)

    println(adder.apply(1))
    println(adder(1))
}