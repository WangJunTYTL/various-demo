#!/bin/sh
#-----------------------------------
# create by wangjun on 2015-08-14
# shell array usage demo
# shell 的唯一集合就是数组：
# 1.数组的索引是随意的，无序的，如果创建时不指定索引值，默认从0开始索引
# 2.数组可以用作map，索引作为key 值作为value
# 3.数组可以用的函数太少：有基本的操作--增删改，其它的都需要自己实现，比如排序可以利用sort命令
#----------------------------

# 数组声明
city=()
# 数组add
city[1]='beijing'
city[2]='henan'
# 数组获取
echo "citys:${city[@]}" # @ * 代表每一个元素
echo "city[0]="${city[0]} # 获取制定元素时不加#号
echo "city lenth:"${#city[*]}
# 数组删除
unset city[1]
echo "citys:${city[@]}"
# 数组追加,直接在index=6的位置新增元素，通过下面输出可以看出数组的索引的无序的
city[6]="hangzhou"
echo "citys:${city[@]}" # citys:henan hangzhou
echo "citys lenth:${#city[*]}" # citys lenth:2
echo "city[6]:${city[6]}" # city[6]:hangzhou
echo "city[2]:${city[2]}" # city[2]:henan
echo "city[0]:${city[0]}" # city[0]:


# 数组索引是无序的，随意指定的，利用这点实现map结构
city['bj']='beijing'
city['hz']='hangzhou'
echo "citys:${city[*]}"
echo "city['bj']=${city['bj']}" # city['bj']=beijing

# 数组的高级用法
echo "citys 0~3:${city[@]:0:3}"
echo "citys:${city[@]}"
echo "replace:${city[@]/hangzhou/杭州}" # replace:杭州 henan 杭州
echo "citys:${city[@]}" # citys:hangzhou henan hangzhou

# 利用sort命令对数组排序
sort_array(){
	_new_arr=( $(
                for el in "$@"
                        do
                                echo "$el"
                        done | sort) )
        echo "${_new_arr[@]}"
}
sort_array ${city[*]}
# 快速创建序列
echo "1~100:"{1..100}
echo "1~66:"$(seq 66)

