package com.ipinyou.dao

import com.ipinyou.mip.analyze.dao.common.CommonAbstractDAO
import com.ipinyou.mip.analyze.model.mysql.Tracking
import com.ipinyou.model.mysql.{Campaign, CampaignAndTracking, CampaignToMip}
import slick.driver.MySQLDriver.api._

object CampaignDao extends CommonAbstractDAO {
  def selectCampaignAndTracking(advertiseid: Long): Map[Long, Option[String]] =
    Campaign.q.join(Tracking.q)
      .on(_.id === _.campaignId)
      .filter { case (campaign, tracking) => campaign.companyId === advertiseid && campaign.removed === 0 && tracking.removed === false }
      .map { case (campaign, tracking) => campaign.id -> (campaign.campaign ?) }
      .result
      .toMap

  def selectCampaignId(advertiseid: Long): Map[Long, Option[Long]] =
    CampaignToMip.q
      .filter { case (campaigntomip) => campaigntomip.companyId === advertiseid && campaigntomip.removed === 0 }
      .map { case (campaigntomip) => campaigntomip.frisoCampaignId -> (campaigntomip.id ?) }
      .result
      .toMap


}
