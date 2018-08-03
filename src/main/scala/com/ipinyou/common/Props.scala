package com.ipinyou.common

import java.util.Properties

object Props {
  private val properties = new Properties()
  properties.load(Thread.currentThread().getContextClassLoader.getResourceAsStream("application.properties"))

  def get(field: String): String = properties.getProperty(field)
}
