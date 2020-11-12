class PersonWithoutConstructor(val firstName: String, val lastName: String) {
    def greet(): Unit = {
        println("Hello " + firstName)
    }
}