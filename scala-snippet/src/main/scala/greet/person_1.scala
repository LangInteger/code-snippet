object person {
    val firstName: String = "Lang"
    val lastName: String = "Integer"

    def greet(p: person.type): Unit = {
        println("Hello " + p.firstName)
    }
}