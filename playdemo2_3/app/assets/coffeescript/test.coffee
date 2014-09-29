#可以直接在浏览器嵌入coffee-script.js 解析xx.coffee脚本,但真正正式使用时不建议这样使用,coffee-script.js下载地址：http://coffeescript.org/extras/coffee-script.js
#本地安装coffee 可以把写好的xx.coffee 编译成 xx.js  命令：coffee -c xx.coffee
#变量
myVar = 1
#数组
myArray = [1, 2, 3, 4, 5]
#对象
leader =
  name: "wj"
  age: 25
#alert(leader.name)
#函数 if x == null x default 6
square = (x = 6) ->
  x * x
#alert(square(myVar))
#alert(square())

#判断
#三目运算
myVar = 0 if leader.name == 'wj'
#alert(myVar)

if myVar == 0
  myVar = 1
else if myVar == 1
  myVar = 2
else
  myVar = 3


#遍历
myVar = myVar + x for x in myArray;
myVar = myVar + x for x in myArray when x == 5
myNewVar = x for x in myArray by 2 # 得到的是最后一个元素
myNewArray = (x for x in myArray by 2) #得到的是新数组
#alert('myNewArray='+myNewArray)


#对象遍历
leaderToString = for k,v of leader
  "#{k} is #{v}" #当在字符串写#{var} var会被作为一种变量解析
#alert(leaderToString)


myVar = 6
while myVar > 5
  myVar--
until myVar == 1 #until = while not
  myVar--
#alert(myVar)

#alert(myArray[0..2]) # 截取数组
#alert(myArray)
#alert(myArray[1...3]) # 截取数组

tip =
  try
    myVar = undefined/undefined
  catch
    "不可以被哦0除#{error}"
alert(tip)

###
CoffeeScript	JavaScript
is	          ===
isnt	        !==
not	          !
and	          &&
or	          ||
true, yes, on	true
false, no, off false
@, this	      this
of	          in
in	        no JS equivalent
a ** b	    Math.pow(a, b)
a // b	    Math.floor(a / b)
a %% b	    (a % b + b) % b
###

#print() 调用打印机

html = """
       <strong>
         cup of coffeescript
       </strong>
       """
alert(html)

#switch
score = 76
grade = switch
  when score < 60 then 'F'
  when score < 70 then 'D'
  when score < 80 then 'C'
  when score < 90 then 'B'
  else 'A'
