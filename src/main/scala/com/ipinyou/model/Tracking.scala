package com.ipinyou.model
import java.sql.{Date, Timestamp}

/**
  * 曝光或点击
  */
case class Tracking
(
  //标识区段
  actionType: String, //请求类别：imp(曝光)、clk(点击)、adv(访客)、cvt(转化)
  uuid: String, //全局唯一ID
  serviceHost: String, //服务域名
  serviceUri: String, //请求的URL
  secure: Option[Boolean], //是否https
  sessionId: String, //会话ID，浏览器未关闭，同一用户一致
  beginTime: Timestamp, //开始时间
  endTime: Timestamp, //结束时间
  beginDate: Date, //开始日期，分区用

  //Cookie区段
  cookieId: String, // 用户在服务器域下的cookie ID
  newCookie: Option[Boolean], //是否新生成的Cookie

  //地理区段
  ip: String, //IpV4
  country: Int, //IP地域：国家，10位IPB标准编码。未知地域值=1000000000
  province: Int, //IP地域：省级行政区（中国为省、自治区、直辖市，美国为州），10位IPB标准编码，如与国家ID一致，表明不知道具体哪个省。未知地域值=1000000000
  city: Int, //IP地域：城市，10位IPB标准编码，如与省ID一致，表明不知道具体哪个城市。未知地域值=1000000000
  longitude: Option[java.lang.Double], //经度
  latitude: Option[java.lang.Double], //纬度

  //网站区段
  url: String, //当前页，u参数
  referUrl: String, //前导页，r参数
  domain: String, //url域名
  referDomain: String, //referUrl域名
  headUrl: String, //从头中获取的URL
  headDomain: String, //headUrl域名

  //App区段
  appName: String, //App 名称
  appVersion: String, //App 版本

  //设备区段
  deviceType: String, //设备类型：pc(pc)、mobile(移动)
  ua: String, //User-Agent
  os: String, //操作系统
  osVersion: String, //操作系统版本
  browser: String, //浏览器
  browserVersion: String, //浏览器版本
  deviceBrand: String, //设备品牌
  deviceModel: String, //设备型号
  osp: String, //媒体定义的操作系统，来自os参数，0：Android，1：iOS， 2：WP，3：其它
  imei: String, //IMEI，md5sum
  mac: String, //MAC地址（大写去除冒号分隔符），md5sum
  mac1: String, //MAC地址（大写保留冒号分隔符），md5sum
  idfa: String, //苹果IDFA，原始值
  aaid: String, //安卓广告ID，原始值
  openUdid: String, //苹果OpenUDID，原始值
  androidId: String, //安卓ID，md5sum
  duid: String, //WindowsPhone DeviceUniqueId，md5sum
  carrier: String, //运营商：cm(中国移动)、cu(中国联通)、ct(中国电信)
  connection: String, //网络：wifi/2g/3g/4g/5g

  //投放活动区段
  campaignId: Long, //投放活动ID
  advertiser: Long, //广告主公司ID
  campaignBrand: String, //投放活动品牌，产品线
  campaignName: String, //投放活动名称
  campaignBeginDate: String, //投放活动开始日期
  campaignEndDate: String, //投放活动结束日期
  campaignLandingPage: String, //投放活动落地页

  //监测信息区段
  trackingId: Option[Long], //监测信息ID
  trackingCampaign: Option[Long], //所属投放活动ID，与campaignId一致
  trackingPublisher: String, //媒体
  trackingVertical: String, //频道
  trackingAdType: String, //广告类型：信息流/视频/Banner等
  trackingAdSlot: String, //广告位
  trackingCreative: String, //创意
  trackingDeviceType: String, //终端类型，PC/Mobile
  trackingRegion: String, //地域
  trackingKpiType: String, //KPI类型：CPM/CPC等
  trackingKpiValue: BigDecimal, //KPI值
  trackingLandingPage: String, //监测落地页
  trackingChannel: String, //渠道

  //跳转区段
  rul: String, //rul参数
  finalUrl: String //最终的跳转地址，优先级：1、rul参数，2、trackingLandingPage，3、campaignLandingPage

)