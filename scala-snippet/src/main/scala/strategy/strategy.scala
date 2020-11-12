package strategy

case class MContext(value: Int) {
    def execute(strategy: Int => Int): Int = {
        return strategy(value);
    }
}

object Main extends App {
    def context = MContext(1);
    println(context.execute(x => x + x))
} 