package com.ipinyou.model

import com.google.protobuf.timestamp.Timestamp


case class FrisoWarehouseModel(
                                campaignId:Long,
                                trackingPublisher:String,
                                trackingadslot:String,
                                imei:String,
                                idfa:String,
                                mac:String,
                                androidId:String,
                                openUdid:String,
                                timestamp:Timestamp
                              )
