class Director(
    val firstName: String, 
    val lastName: String, 
    val yearOfBirth: Int) {

    def name(): String = {
        "%s %s".format(firstName, lastName)
    }
}