package com.ipinyou.spark

import java.sql.Timestamp
import java.{sql, util}
import java.util.{Date, UUID}

import com.ipinyou.model.{FrisoLogModel, Tracking}
import com.ipinyou.common.{CastFromString, FtpUtils, Props}
import com.ipinyou.dao.CampaignDao
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SQLContext, SaveMode, SparkSession}
import com.ipinyou.mip.analyze.common._


object Data {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("wordcount")
    val spark = SparkSession
      .builder()
      .appName("FrisoDataToWarehouse")
      .config(conf)
      .getOrCreate()
    val hdfsRoot: String = Props.get("hdfs.root")
    val frisoCompany: Long = Props.get("advertiser").toLong
    var tableName: String = Props.get("warehouse.click")
    val hdfsPath = s"$hdfsRoot/$args(0)/*.gz"
    var deviceType = "pc"
    var actionType = "imp"

    val frisoCampaign: Map[Long, Option[Long]] = CampaignDao.selectCampaignId(frisoCompany)

    if (args(0) != null) {
      val typeAndDevice = args(0).trim.toLowerCase
      deviceType = if (typeAndDevice.contains("mobile") || typeAndDevice.contains("pc") || typeAndDevice.contains("ott")) typeAndDevice else null
      if (typeAndDevice.contains("click")) {
        actionType = "clk"
      } else if (typeAndDevice.contains("view")) {
        actionType = "imp"
      }
    }

    runSpark(spark, hdfsPath, actionType, deviceType, frisoCompany, tableName, frisoCampaign)
  }

  def runSpark(spark: SparkSession, hdfsPath: String, actionType: String, deviceType: String, frisoCompany: Long, tableName: String, frisoCampaign: Map[Long, Option[Long]]): Unit = {
    val context: SparkContext = spark.sparkContext
    val fileRdd: RDD[String] = context.textFile(hdfsPath)
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
      if (wordsArr.apply(10) != null) Some(new Timestamp(wordsArr.apply(10).trim.toLong * 1000)) else null,
      CastFromString.String2Int(wordsArr.apply(11))

    ))
    val frisoLogModelsFilter: RDD[FrisoLogModel] = frisoLogModels.filter(
      fri => {
        fri.campaignId != null
      })
    val frisoWarehouseModels: RDD[Tracking] = frisoLogModelsFilter.flatMap(fri => {
      for (i <- 1 to fri.count) yield
        Tracking(actionType, UUID.randomUUID().toString.replaceAll("-", ""),
          null, null, null, null, fri.timestamp.get, fri.timestamp.get, new sql.Date(fri.timestamp.getOrElse(new Timestamp(System.currentTimeMillis())).getTime), null, null, null,
          1000000000, 1000000000, 1000000000, null, null, null, null, null, null, null, null, null, null, deviceType, null, null, null, null, null, null, null, null, fri.imei, fri.mac, null, fri.idfa,
          null, fri.openUdid, fri.androidId, null, null, null, frisoCampaign.get(fri.campaignId).get.get, frisoCompany, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
          null)
    })
    /* val output = hdfsRoot + s"/rpt_effect_base/advertiser=$frisoCompany/begindate=$"
     HDFS.delete(output, spark)
     spark.createDataFrame(frisoWarehouseModels).write.parquet(output)
     Hive.msckRepair(spark, "default.rpt_effect_base")*/
    val sqlContext: SQLContext = spark.sqlContext

    val df: DataFrame = sqlContext.createDataFrame(frisoWarehouseModels)
    df.write.format("parquet").mode(SaveMode.Append).partitionBy("advertiser", "beginDate").saveAsTable(tableName)
  }


}
