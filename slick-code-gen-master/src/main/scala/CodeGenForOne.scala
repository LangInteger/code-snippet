import slick.codegen.SourceCodeGenerator
import slick.driver.JdbcProfile
import slick.model.Model

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

object CodeGenForOne extends App {

  val slickDriver = "slick.jdbc.MySQLProfile"
  val jdbcDriver = "com.mysql.cj.jdbc.Driver"
  val url = "jdbc:mysql://20.20.20.66:30569/tmc_services"
  val outputFolder = "./generated"
  val pkg = "com.myorg"
  val user = "root"
  val password = ""

  def run(
      slickDriver: String,
      jdbcDriver: String,
      url: String,
      outputDir: String,
      pkg: String,
      user: Option[String],
      password: Option[String]
  ): Unit = {

    val driver: JdbcProfile =
      Class
        .forName(slickDriver + "$")
        .getField("MODULE$")
        .get(null)
        .asInstanceOf[JdbcProfile]
    val dbFactory = driver.api.Database
    val db = dbFactory.forURL(
      url,
      driver = jdbcDriver,
      user = user.orNull,
      password = password.orNull,
      keepAliveConnection = true
    )
    try {
      // **1**
      val allSchemas = Await.result(
        db.run(
          driver
            .createModel(None, ignoreInvalidDefaults = false)(
              ExecutionContext.global
            )
            .withPinnedSession
        ),
        Duration.Inf
      )
      // **2**
      val publicSchema = new Model(
        allSchemas.tables.filter( table =>table.name.asString.equals("tmc_services.insurance_order")),
        allSchemas.options
      )
      // **3**
      new SourceCodeGenerator(publicSchema)
        .writeToFile(slickDriver, outputDir, pkg)
    } finally db.close
  }
  
  CodeGenForOne.run(
    slickDriver,
    jdbcDriver,
    url,
    outputFolder,
    pkg,
    Some(user),
    Some(password)
  )

}
