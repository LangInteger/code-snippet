package strategy.nested

case class MContext(value: Int) {
    def execute(strategy: Int => MContext): MContext = {
        return strategy(value);
    }
}

object Main extends App {
    def context1 = MContext(1);
    def context2 = MContext(2)
    def context3 = MContext(3)

    val result = context1.execute(v1 => 
        context2.execute(v2 =>
            context3.execute(v3 => MContext(v1 + v2 + v3))));
    
    print(result.value)

    val a = Option[Int](1)
} 