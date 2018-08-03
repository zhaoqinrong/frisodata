#!/bin/bash
date=$1
cd ~/Friso/data
if [ x"$date" = x ]; then
        date=`date +%Y%m%d`	
	echo $date
fi
lftp Friso_r:XcwMHfcV0s@ftpsupply.trackmaster.com.cn:21 <<EOF
cd Friso
mirror $date
bye
EOF
