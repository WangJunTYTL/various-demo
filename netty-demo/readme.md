netty
===========

### 官网3个demo

discard协议：server端收到客户端的请求，服务端丢弃收到的请求，彼此连接不会关闭

echo协议:服务端收到客户端的请求消息后，服务端把收到的消息在原样返回到客户端，这时彼此连接没有关闭

Time协议:客户端和服务端建立连接时，服务端立马向客户端写回服务端的时间，客户端收到后关闭连接

time协议又逐步讲述了几点：

1. server 与 client的模式
2. 网络传输出，传输的字节流出现无规则的问题
3. 引入decode对字节码进行解码的编写模式

如果这三个demo基本学会了，那我们可以上手实现自己的网络传输协议了。

* `ByteBuf`是收到的最原始的数据，字节流数据。
* `Channel`是代表socket与字节流关联的操作，比如read、write
* `ChannelHandler`是对Channel的处理，代表connection过程，比如`channelActive`方法是在连接建立时触发的方法`channelRead`方法代表当收到请求数据时应该调用的方法



### 个别对象

【Channel】A nexus to a network socket or a component which is capable of I/O operations such as read, write, connect, and bind.

表示socket和I/O(a hardware device, a file, a network socket etc)操作的关系

【ChannelHandle】


【ChannelPipeline】

【ChannelOption】

【ChannelConfig】

【ChannelHandlerContext】A ChannelHandlerContext object provides various operations that enable you to trigger various I/O events and operations. 

【ByteBuf】最原始的数据，字节


### java 各类型字节占用

	1个字节是8位
	只有8种基本类型可以算.其他引用类型都是由java虚拟机决定的自己不能操作
	byte 1字节
	short 2字节
	int 4字节
	long 8字节
	float 4字节
	double 8字节
	char 2字节
	boolean 1字节



### http协议


	GET / HTTP/1.1
	Host: 127.0.0.1:8080
	User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:40.0) Gecko/20100101 Firefox/40.0
	Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
	Accept-Language: en-US,en;q=0.5
	Accept-Encoding: gzip, deflate
	Cookie: _gauges_unique_month=1; _gauges_unique_year=1; _gauges_unique=1
	Connection: keep-alive
	Cache-Control: max-age=0
	
### tcp协议




