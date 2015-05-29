#!/usr/bin/env bash
#---------------------------------------------
# linux test 命令学习
#-------------------------------------------------------------------------------


#-------------------------------------------------------------------------------
#
#   1. 关于某个文件名的『类型』侦测(存在与否)，如 test -e filename
#   -e 该『文件名』是否存在？(常用)
#   -f 该『文件名』是否为文件(file)？(常用)
#   -d 该『文件名』是否为目录(directory)？(常用)
#   -b 该『文件名』是否为一个 block device 装置？
#   -c 该『文件名』是否为一个 character device 装置？
#   -S 该『文件名』是否为一个 Socket 文件？
#   -p 该『文件名』是否为一个 FIFO (pipe) 文件？
#   -L 该『文件名』是否为一个连结档？

#   2. 关于文件的权限侦测，如 test -r filename
#   -r 侦测该文件名是否具有『可读』的属性？
#   -w 侦测该文件名是否具有『可写』的属性？
#   -x 侦测该文件名是否具有『可执行』的属性？
#   -u 侦测该文件名是否具有『SUID』的属性？
#   -g 侦测该文件名是否具有『SGID』的属性？
#   -k 侦测该文件名是否具有『Sticky bit』的属性？
#   -s 侦测该文件名是否为『非空白文件』？

#   3. 两个文件之间的比较，如： test file1 -nt file2
#   -nt (newer than)判断 file1 是否比 file2 新
#   -ot (older than)判断 file1 是否比 file2 旧
#   -ef 判断 file2 与 file2 是否为同一文件，可用在判断 hard link 的判定上。 主要意义在判定，两个文件是否均指向同一个 inode 哩！

#   4. 关于两个整数之间的判定，例如 test n1 -eq n2
#   -eq 两数值相等 (equal)
#   -ne 两数值不等 (not equal)
#   -gt n1 大于 n2 (greater than)
#   -lt n1 小于 n2 (less than)
#   -ge n1 大于等于 n2 (greater than or equal)
#   -le n1 小于等于 n2 (less than or equal)

#   5. 判定字符串的数据
#   test -z string 判定字符串是否为 0 ？若 string 为空字符串，则为 true
#   test -n string 判定字符串是否非为 0 ？若 string 为空字符串，则为 false。
#   注： -n 亦可省略
#   test str1 = str2 判定 str1 是否等于 str2 ，若相等，则回传 true
#   test str1 != str2 判定 str1 是否不等于 str2 ，若相等，则回传 false

#   6. 多重条件判定，例如： test -r filename -a -x filename
#   -a (and)两状况同时成立！例如 test -r file -a -x file，则 file 同时具有 r 与 x 权限时，才回传 true。
#   -o (or)两状况任何一个成立！例如 test -r file -o -x file，则 file 具有 r 或 x 权限时，就可回传 true。
#   ! 反相状态，如 test ! -x file ，当 file 不具有 x 时，回传 true
#
#----------------------------


. ENE

echo $Name

if [ -z "$Name" ] ; then
    echo ${Email}
fi
