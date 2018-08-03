#!/bin/bash
dirPre=$2
date=$1
hadoopPre='/data/Friso'
localDataPre='/home/mip/Friso/data'
echo $localDataPre
if [ x"$date" = x ]; then
        date=`date +%Y%m%d`
fi
 echo $date

if [ -z "$dirPre" ]
then
	dirPre='/home/mip/Friso/temp'
fi
successPath=`find $dirPre/$date -name SUCCESS`
for i in ${successPath[@]}
do
	filepath=${i##*$date}
	path=${filepath%/*}
	dirPath="${date}${path}"
	logPath="${dirPre}/$dirPath"
	mkdir -p $localDataPre/$dirPath
	cp -n $logPath/* $localDataPre/$dirPath
	echo $dirPath>>$localDataPre/$date/success.log
	newFilePath=`sort $localDataPre/$date/success.log | uniq -c | sort -rn |awk -F' ' '{if($1==1){ print $2}}'`
	#hadoop fs -mkdir -p $hadoopPre/$dirPath
	#hadoop fs -put $localDataPre/$dirPath/* $hadoopPre/$dirPath
done
for file in ${newFilePath[@]}
do
	echo $file
#	hadoop fs -mkdir -p $hadoopPre/$file
#	hadoop fs -put $localDataPre/$file/* $hadoopPre/$file
	#调用spark
done
