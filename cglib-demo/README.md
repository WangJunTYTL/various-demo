## asm cglib  动态代理

### java序列化
java对象可以被序列化为字节流，存在磁盘或在网上传输，同时也可以反序列化，利用这个特点可以实现rmi，也是本章要说的asm为什么可以解析class文件
### asm
asm是一个分析和改变class文件的工具，它可以通过字节流读取一个class，分析class文件，可以知道这个class的name ，file，annotation，superClass 等等。
同时asm也可以修改class文件，在java runtime时被classLoader load.
要想对asm熟练的使用，需要开发人员对class文件的结构比较了解，这是一个高级的应用
asm解析class文件，我对core api的理解是：它把class文件解析为一颗树,树的节点有这些，class，file ，annotation，method等等，ClassReader解析时遇到这些节点分布会调用对应的
classVisitor,FileVisitor，methodVisitor等visitor，classWriter也可以改变重新生成class文件
asm官网[http://asm.ow2.org/]
### cglib
cglib是一个基于asm的操作字节码工具包。它透明化了asm的操作，开发人员可以在不熟悉class文件结构的情况下就开以使用
### 动态代理
动态代理是指：当我们调用一个类的方法时，不是直接调用了这个类的方法，拿cglib来说，cglib会通过Enhancer类生成一个类的代理类，比如你有一个类A,通过Enhance
你可以得到一个继承A类的类A$enhance,所以你调用的实例变成了A$enhance，利用Enhance你可以打断你调用的真正方法的执行，来达到你需要目的。
### projects
hibernate spring 都有用到，比如spring自动扫描某包下得bean，就是利用asm读取该包下得文件，分析每个class文件是否是spring容器需要管理的。

对于开发人员来说这是一个高级的应用，但这方面的文档特别少，我也是正在摸索中学习，下面是我自己学习的一些文档

1. cglib[https://github.com/cglib/cglib/wiki/Tutorial]
1. cglib[http://mydailyjava.blogspot.no/2013/11/cglib-missing-manual.html]
1. asm官网[http://asm.ow2.org/]
1. http://www.ibm.com/developerworks/cn/java/j-lo-asm/
1. http://my.oschina.net/u/1166271/blog/162796
1. demo[https://github.com/WangJunTYTL/myapp/tree/master/cglib-demo]
1. javassist[http://www.csg.ci.i.u-tokyo.ac.jp/~chiba/javassist/]
1.
1. http://my.oschina.net/u/1166271/blog/386904




