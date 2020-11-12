package com.langinteger.utils.exception

case class ApplicationException(code: Int, message: String) extends RuntimeException(message: String) {
}
