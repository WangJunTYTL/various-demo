#!/usr/bin/env bash
#--------------------------------
# create by wangjun on 2015-06-26
# 1.shell 的函数定义，注意变量的作用的域，默认是global， 定义局部变量 local var='x'
# 2.方法传参 $1.2.3代表对应位置的参数 $@ 或 $*  所有参数"
#------------------------------
func_a(){
 name='jj' # 默认全局变量
 local sex='1' # 函数局部变量
}
echo "name="$name" sex="$sex # name= sex=
# 函数执行
func_a
echo "name="$name" sex="$sex # name=jj sex=
fun_a(){
    echo $@ # 所有参数列表。如"$*"用「"」括起来的情况、以"$1 $2 … $n"的形式输出所有参数
    echo $* # 所有参数列表。如"$@"用「"」括起来的情况、以"$1" "$2" … "$n" 的形式输出所有参数。
    echo $1
}
fun_a
fun_a "a" "b"



