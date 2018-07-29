package com.ipinyou.common


import scala.util.matching.Regex


object RegexUtils {
  def StringRegex(str: String): Boolean = {
    if (str != null) {
      str.length > 20
    } else {
      true
    }

  }
  def timeStampRegex(time: Long) :Boolean={
    if (time!=null){
      time.toString.length==10
    }else{
      true
    }
  }
}
