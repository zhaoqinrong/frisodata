/*
package com.ipinyou.spark

import com.ipinyou.common.FTPToHdfs
import org.apache.spark.sql.SparkSession


class FrisoFTPToHdfs {
  def copyFtpToHdfs(spark:SparkSession, dateStr:String): Boolean ={
    val bool: Boolean = FTPToHdfs.FTPCheck(dateStr)
    if(!bool){
      false
    }
//    ftpPath: String, ftpFile: String, localPath: String, hdfsPath: String
    var ftpPath=s"Friso/$dateStr"
    val
    FTPToHdfs.FTPTohdfs(spark,ftpPath,)
  }


}
*/
