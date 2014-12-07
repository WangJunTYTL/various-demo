#/bin/bash

function send(){
	python ./sendmail.py -s 测试send --content=helloworld  -f 867516002@qq.com -t wangjuntytl@163.com --host=smtp.qq.com -u 867516002@qq.com -p woshinidayeaawj smtp_host = smtp.qq.com smtp_port = 25 smtp_user = 867516002@qq.com  smtp_password = **********
}

for((i=1;i<2;i++))
do
	echo $i

done

python ./sendmail.py -s 测试send --content=helloworld  -f 867516002@qq.com -t wangjuntytl@163.com --host=smtp.qq.com -u 867516002@qq.com -p woshinidayeaawj smtp_host = smtp.qq.com smtp_port = 25 smtp_user = 867516002@qq.com  smtp_password = woshinidayeaawj


