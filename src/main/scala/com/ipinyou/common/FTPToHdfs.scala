package com.ipinyou.common

import java.util

import com.ipinyou.mip.analyze.common.TimeFormatter
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession

object FTPToHdfs {
  val ftpUtils = new FtpUtils()

  val hdfs: FileSystem = FileSystem.get(new Configuration)

  def FTPTohdfs(spark: SparkSession, ftpPath: String, ftpFile: String, localPath: String, hdfsPath: String): Unit = {
    ftpUtils.initFtpClient()
    ftpUtils.downloadFile(ftpPath, ftpFile, localPath)
    hdfs.copyFromLocalFile(new Path(localPath), new Path(hdfsPath))
  }
  def FTPTohdfsDir(spark: SparkSession, ftpPath: String, localPath: String, hdfsPath: String): Unit = {
    ftpUtils.initFtpClient()
    ftpUtils.downLoadDirectory(localPath,ftpPath)
    hdfs.copyFromLocalFile(new Path(localPath), new Path(hdfsPath))
  }

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .appName("zhangsan")
      .master("local")
      .getOrCreate()
    FTPTohdfsDir(spark,"Friso/20180730/click/mobile","E://","")
  }

  def FTPCheck(dateStr: String): Boolean = {
    val click: Boolean = checkSuccessFile("click", dateStr)
    val view: Boolean = checkSuccessFile("view", dateStr)
    if (click && view) {
      true
    }
    false
  }

  def checkSuccessFile(clkimp: String, dateString: String): Boolean = {
    val ftpUtils = new FtpUtils()
    var str = dateString
    if (dateString == null) {
      val date = new java.sql.Date(System.currentTimeMillis())
      str = TimeFormatter.Y4M2D2.format(date)
    }

    val fipath = s"Friso/$str/$clkimp"
    ftpUtils.initFtpClient()
    val pathList: util.List[String] = ftpUtils.listAllDir(fipath)
    var flag = true
    if (pathList == null || pathList.isEmpty) {
      return false
    }

    val it: util.Iterator[String] = pathList.iterator()
    while (it.hasNext) {
      val str: String = it.next()
      val pathName = s"$fipath/$str"
      ftpUtils.initFtpClient()
      val existFile: Boolean = ftpUtils.existFile(s"$pathName/SUCCESS")
      if (!existFile) {
        flag = false
      }
    }

    return flag
  }


}
