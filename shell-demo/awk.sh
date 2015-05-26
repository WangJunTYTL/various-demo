#!/usr/bin/env bash
#bin/bash

LAST=''; 

lastday()  
{  
  Today=`date +%Y%m%d`  
  YEAR=`echo $Today|cut -c 1-4`  
  MONTH=`echo $Today|cut -c 5-6`  
  DAY=`echo $Today|cut -c 7-8`  
  if [ $DAY -eq 1 ]  
  then  
    if [ $MONTH -eq 1 ]  
    then  
      YEAR=`expr $YEAR - 1`  
      MONTH=12  
    else  
      MONTH=`expr $MONTH - 1`  
    fi  
#如下两种方法皆可  
    #DAY=`echo \`cal $MONTH $YEAR\`|tail -n1|awk '{print $NF}'`  
#   
   DAY=$(cal $MONTH $YEAR | tail -n2|sed -n 1,1p | awk '{print $NF}')  
  
  else  
    DAY=`expr $DAY - 1`  
  fi  
  LAST=$(echo "$YEAR $MONTH $DAY"|awk '{if (length($2)==1) $2=0$2;if (length($3)==1) $3=0$3;printf "%s%s%s",$1,$2,$3}')  
}  

lastday 1