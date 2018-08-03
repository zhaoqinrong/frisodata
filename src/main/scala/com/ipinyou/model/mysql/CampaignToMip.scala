package com.ipinyou.model.mysql


import slick.driver.MySQLDriver.api._

case class CampaignToMip(id: Long, version: Long, createTime: String, lastModified: String, companyId: Long, frisoCampaignId:Long, removed: Int)
object CampaignToMip {

    class T(tag: Tag) extends Table[CampaignToMip](tag, "campaign_to_mip") {
      def id = column[Long]("id")

      def version = column[Long]("version")

      def createTime = column[String]("create_time")

      def lastModified = column[String]("last_modified")

      def companyId = column[Long]("company_id")

      def frisoCampaignId=column[Long]("friso_campaign_id")

      def removed = column[Int]("removed")

      def * = (id, version, createTime, lastModified, companyId, frisoCampaignId, removed) <> ((CampaignToMip.apply _).tupled, CampaignToMip.unapply)
    }
    val q = TableQuery[T]

}
