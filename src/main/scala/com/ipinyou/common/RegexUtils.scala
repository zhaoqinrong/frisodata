package com.ipinyou.common

import com.ipinyou.mip.analyze.common.TimeFormatter.Pattern
import org.scalatest.matchers.Matcher

import scala.util.matching.Regex


object RegexUtils {
  def StringRegex(str:String,pattern:String):Boolean={
    val regex = new Regex(pattern)
    regex.findAllIn(str)
  }
}
