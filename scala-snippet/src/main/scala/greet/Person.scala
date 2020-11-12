class Person(first: String, last: String) {
    val firstName: String = first
    val lastName: String = last
    def greet() {
        println("Hello " + firstName)
    }
}