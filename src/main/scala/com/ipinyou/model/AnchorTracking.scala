package com.ipinyou.model


case class AnchorTracking(
                           userflag: String, //用户标识位
                           tid: String, //cookie
                           actiontype: String, //行为（点击/曝光/访问）
                           sessionid: String, //session
                           requesttime: String, //请求时间
                           siteflag: String, //站点标识位
                           url: String, //当前URL
                           refurl: String, //前导URL
                           appflag: String, //APP标识位
                           appid: String, //APPID
                           bundle: String, //APP包名
                           appname: String, //APP名称
                           deviceflag: String, //设备信息标识位
                           ua: String, //访客请求UA信息
                           devicetype: String, //设备种类
                           maker: String, //设备制造商
                           model: String, //设备品牌
                           os: String, //操作系统
                           osversion: String, //系统版本
                           height: String, //设备显示高度
                           width: String //设备显示宽度
//                           carrier: String, //运营商
//                           connectiontype: String, //连接种类（2G/3G/4G）
//                           idfa: String, //IDFA
//                           idfaenc: String, //IDFA加密类型
//                           imei: String, //IMEI
//                           imeienc: String, //IMEI加密类型
//                           aid: String, //安卓ID
//                           aidenc: String, //安卓ID加密类型
//                           mac: String, //MAC地址
//                           macenc: String, //MAC地址加密类型
//                           latitude: String, //纬度信息
//                           longtitude: String, //经度信息
//                           city: String, //所在城市
//                           ipv4: String, //IP地址
//                           aaid: String, //AAID
//                           openudid: String, //OPENUDID
//                           duid: String, //DUID
//                           duidenc: String, //DUID加密类型
//                           pyid: String, //品友ID
//                           adslotflag: String, //广告位标识位
//                           mediaid: String, //媒体ID
//                           tagid: String, //广告位ID
//                           tagtype: String, //广告位类型
//                           campaignid: String, //投放campaign
//                           creativeid: String, //创意ID
//                           monitorflag: String, //监测点标识位
//                           siteid: String, //主站ID
//                           monitorid: String, //监测点ID
//                           pday: String
                         )
