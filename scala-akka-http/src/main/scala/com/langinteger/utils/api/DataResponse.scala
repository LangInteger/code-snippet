package com.langinteger.utils.api

case class DataResponse[A](
  code: Int = CommonCode.SUCCESS_CODE,
  message: String = CommonCode.SUCCESS_MESSAGE,
  data: A = null
)
