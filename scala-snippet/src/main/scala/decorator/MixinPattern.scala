package decorator

object MixinPattern extends App {

    trait ShapeDecorator{ self: Shape =>
        private def setRedBorder(decoratedShape: Shape): Unit = {
            println("Border Color: Red")
        }

        override def draw(): Unit = {
            self.draw();
            setRedBorder(self);
        }
    }

    val circle = new Circle() with ShapeDecorator
    circle.draw()
}


