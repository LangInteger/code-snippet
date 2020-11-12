package com.langinteger.utils

import com.typesafe.config.{Config, ConfigFactory}

/**
  * 加载程序启动配置的类
  */
trait Configuration {

  protected val config: Config = ConfigFactory.load()
  private val httpConfig = config.getConfig("http")
  private val appConfig = config.getConfig("app")

  // Application
  val applicationName: String = appConfig.getString("name")

  // Http
  val httpHost: String = httpConfig.getString("interface")
  val httpPort: Int = httpConfig.getInt("port")

}