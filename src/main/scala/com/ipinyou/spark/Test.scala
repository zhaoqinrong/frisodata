package com.ipinyou.spark

import java.sql.Timestamp

import com.ipinyou.dao.CampaignDao
import com.ipinyou.model.mysql.CampaignToMip
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object Test {
  def main(args: Array[String]): Unit = {
 /*   val longToMaybeString: Map[Long, Option[String]] = CampaignDao.selectCampaignAndTracking(11)
    longToMaybeString.foreach(print)*/
 val schemaString = "actionType,uuid,serviceHost,serviceUri,secureOption[Boolean]sessionId,beginTimeTimestamp,endTimeTimestamp,beginDateDate,cookieId,newCookie,ip,countryInt,provinceInt,cityInt,longitude,latitude,url,referUrl,domain,referDomain,headUrl,headDomain,appName,appVersion,deviceType,ua,os,osVersion,browser,browserVersion,deviceBrand,deviceModel,osp,imei,mac,mac1,idfa,aaid,openUdid,androidId,duid,carrier,connection,campaignIdLong,advertiserLong,campaignBrand,campaignName,campaignBeginDate,campaignEndDate,campaignLandingPage,trackingId,trackingCampaign,trackingPublisher,trackingVertical,trackingAdType,trackingAdSlot,trackingCreativetrackingDeviceTypetrackingRegion,trackingKpiType,trackingKpiValuetrackingLandingPagetrackingChannel,rul,finalUrl"
    val schemas: Array[String] = schemaString.split(",")



  }

}
