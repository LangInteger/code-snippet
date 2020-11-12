package com.langinteger.services.log

import org.slf4j.{Logger, LoggerFactory}

/**
  * 记录日志
  */
case class LogService() {

  private lazy val LOG: Logger = LoggerFactory.getLogger(getClass)

  def log(record: String): Unit = {
    LOG.info("APPLOG: {}", record)
  }

}
