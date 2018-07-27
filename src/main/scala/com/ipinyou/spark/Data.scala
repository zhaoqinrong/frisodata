package com.ipinyou.spark

import com.ipinyou.model.{FrisoLogModel, FrisoWarehouseModel}
import com.ipinyou.common.CastFromString
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object Data {
  val imeiPattern="[0-9a-zA-Z]{32}"
  val idfaPattern="[0-9a-zA-Z-]{36}"
  val idfaPattern="[0-9a-zA-Z-]{36}"
  def main(args: Array[String]): Unit = {
    val context: SparkContext = SparkContext.getOrCreate()
    val fileRdd: RDD[String] = context.textFile("")
    val words: RDD[Array[String]] = fileRdd.map(line => {
      line.split("\t")
    })

    val frisoLogModels: RDD[FrisoLogModel] = words.map(wordsArr => FrisoLogModel(
      CastFromString.String2Long(wordsArr.apply(0)),
      wordsArr.apply(1),
      wordsArr.apply(2),
      wordsArr.apply(3),
      wordsArr.apply(4),
      wordsArr.apply(5),
      wordsArr.apply(6),
      wordsArr.apply(7),
      wordsArr.apply(8),
      wordsArr.apply(9),
      CastFromString.String2Long(wordsArr.apply(10)),
      CastFromString.String2Int(wordsArr.apply(11))

    ))
    frisoLogModels.filter(
      fri=>fri.campaignId!=null&&fri.imei!=null&&fri.imei.regex(imeiPattern))
  }
}
