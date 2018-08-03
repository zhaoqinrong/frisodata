package com.ipinyou.model

import java.sql.Timestamp


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
                          timestamp:Option[Timestamp],
                          count: Int
                     )
