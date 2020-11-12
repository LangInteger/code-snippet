
object CodeGen extends App {

    val profile = "slick.jdbc.MySQLProfile"
    val jdbcDriver = "com.mysql.cj.jdbc.Driver"
    val url = "jdbc:mysql://20.20.20.66:30569/tmc_services"
    val outputFolder = "./generated"
    val pkg = "org.myorg"
    val user = "root"
    val password = ""

    slick.codegen.SourceCodeGenerator.main(
        Array(profile, jdbcDriver, url, outputFolder, pkg, user, password)
    )
}