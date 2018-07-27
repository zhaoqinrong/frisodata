package com.ipinyou.model

import com.google.protobuf.timestamp.Timestamp
import org.apache.spark.sql.catalyst.expressions.aggregate.Count


case class FrisoLogModel(
                     campaignId:Long,
                     trackingPublisher:String,
                     trackingadslot:String,
                     imei:String,
                     idfa:String,
                     idfaMd5:String,
                     mac:String,
                     androidId:String,
                     openUdid:String,
                     unknown:String,
                     timestamp:Long,
                     count: Int
                     )
