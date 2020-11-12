package decorator

case class Circle() extends Shape {
    override def draw(): Unit = {
        print("Draw circle")
    }
}