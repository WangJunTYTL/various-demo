#!/bin/sh

function send(){
	python ./sendmail.py -s "测试send" --content=helloworld  -f ************@qq.com -t wangjuntytl@163.com --host=smtp.qq.com -u ************@qq.com -p ************ smtp_host = smtp.qq.com smtp_port = 25 smtp_user = ************@qq.com  smtp_password = **********
}

for((i=1;i<2;i++))
do
	echo $i

done

python ./sendmail.py -s "测试send" --content=helloworld  -f ************@qq.com -t wangjuntytl@163.com --host=smtp.qq.com -u ************@qq.com -p ************ smtp_host = smtp.qq.com smtp_port = 25 smtp_user = ************@qq.com  smtp_password = ************


