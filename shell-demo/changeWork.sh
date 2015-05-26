#!/bin/sh

if [ -f "/Users/wangjun/.m2/settingsHehua.xml" ]; then 
mv /Users/wangjun/.m2/settings.xml /Users/wangjun/.m2/settingsDajie.xml
mv /Users/wangjun/.m2/settingsHehua.xml /Users/wangjun/.m2/settings.xml
sudo -S mv /etc/ssh_config /etc/ssh_configBack
echo "切到H"
elif [ -f "/Users/wangjun/.m2/settingsDajie.xml" ]; then
mv /Users/wangjun/.m2/settings.xml /Users/wangjun/.m2/settingsHehua.xml
mv /Users/wangjun/.m2/settingsDajie.xml /Users/wangjun/.m2/settings.xml
sudo -S mv /etc/ssh_configBack /etc/ssh_config
echo "切到D"
fi
