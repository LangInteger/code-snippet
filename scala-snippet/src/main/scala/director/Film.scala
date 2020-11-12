class Film(
    val name: String, 
    val yearOfRelease: Int, 
    val imdbRating: Double, 
    val director: Director) {

    def directorsAge(): Int = ???

    def isDirectedBy(target: Director): Boolean = {
        target.firstName == director.firstName && target.lastName == director.lastName && target.yearOfBirth == director.yearOfBirth
    }
}