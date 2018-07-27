package com.ipinyou.common

import scala.util.Try


object CastFromString {
   def String2Long(s: String): Long = {
    Try(s.toLong).getOrElse(0L)
  }

  def String2Int(str: String): Int = {
    Try(str.toInt).getOrElse(0)
  }
}
