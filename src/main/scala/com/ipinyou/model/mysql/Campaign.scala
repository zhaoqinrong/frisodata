package com.ipinyou.model.mysql

import slick.driver.MySQLDriver.api._
case class Campaign(id: Long, version: Long, createTime: String, lastModified: String, companyId: Long, brand: String, brandId: Long, campaign: String, beginDate: String, endDate: String, url: String, removed: Int)

object Campaign {
  class T(tag: Tag) extends Table[Campaign](tag, "campaign") {
    def id = column[Long]("id")
    def version = column[Long]("version")
    def createTime = column[String]("create_time")
    def lastModified = column[String]("last_modified")
    def companyId = column[Long]("company_id")
    def brand = column[String]("brand")
    def brandId = column[Long]("brand_id")
    def campaign = column[String]("campaign_name")
    def beginDate = column[String]("begin_date")
    def endDate = column[String]("end_date")
    def url = column[String]("url")
    def removed = column[Int]("removed")
    def * = (id, version, createTime, lastModified, companyId, brand, brandId, campaign, beginDate, endDate, url, removed) <> ((Campaign.apply _).tupled, Campaign.unapply)
  }

  val q = TableQuery[T]
}
