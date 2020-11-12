object PersonWithKeywordParameter {
    def greet(firstName: String = "DefaultFirstName", lastName: String = "DefaultLastName"):Unit = {
        println("Hello " + firstName + " " + lastName)
    }
}