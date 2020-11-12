object FooMain extends App {

    // This is ok
    val booleanResult1 = true && true
    println(booleanResult1)

    // from https://stackoverflow.com/questions/12335388/line-continuation-character-in-scala
    // Scala does not have a "line continuation character" - it infers a semicolon always when:
    // - An expression can end
    // - The following (not whitespace) line begins not with a token that can start a statement
    // - There are no unclosed ( or [ found before
    val booleanResult2 = true && 
    true
    println(booleanResult2)
}