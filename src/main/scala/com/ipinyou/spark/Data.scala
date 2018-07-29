package com.ipinyou.spark

import com.ipinyou.model.{FrisoLogModel, FrisoWarehouseModel}
import com.ipinyou.common.{CastFromString, RegexUtils}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.collection.mutable.ArrayBuffer

object Data {
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
      if (wordsArr.apply(3) != null && wordsArr.apply(3).length > 20) wordsArr.apply(3) else null,
      if (wordsArr.apply(4) != null && wordsArr.apply(4).length > 20) wordsArr.apply(4) else null,
      if (wordsArr.apply(5) != null && wordsArr.apply(5).length > 20) wordsArr.apply(5) else null,
      if (wordsArr.apply(6) != null && wordsArr.apply(6).length > 20) wordsArr.apply(6) else null,
      if (wordsArr.apply(7) != null && wordsArr.apply(7).length > 20) wordsArr.apply(7) else null,
      if (wordsArr.apply(8) != null && wordsArr.apply(8).length > 20) wordsArr.apply(8) else null,
      wordsArr.apply(9),
      CastFromString.String2Long(wordsArr.apply(10)),
      CastFromString.String2Int(wordsArr.apply(11))

    ))
    val frisoLogModelsFilter: RDD[FrisoLogModel] = frisoLogModels.filter(
      fri => {
        fri.campaignId != null
      })
    val frisoWarehouseModels: RDD[FrisoWarehouseModel] = frisoLogModelsFilter.flatMap(fri => {
      for (i <- 1 to fri.count) yield
        FrisoWarehouseModel(fri.campaignId, fri.trackingPublisher, fri.trackingadslot, fri.imei, fri.idfa, fri.mac, fri.androidId, fri.openUdid, fri.timestamp)
    })
  }
}
