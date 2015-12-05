# Avro

Apache Avro™ is a data serialization system.

Avro provides:

Rich data structures.
A compact, fast, binary data format.
A container file, to store persistent data.
Remote procedure call (RPC).
Simple integration with dynamic languages. Code generation is not required to read or write data files nor to use or implement RPC protocols. Code generation as an optional optimization, only worth implementing for statically typed languages.


### rpc

我们在编写自己的rpc服务是,需要定义自己的rpc协议

### maven avro plugin

mvn avro:help 查看插件的帮助信息

avro:idl-protocol
  Generate Java classes and interfaces from AvroIDL files (.avdl)

avro:protocol
  Generate Java classes and interfaces from Avro protocol files (.avpr)

avro:schema
  Generate Java classes from Avro schema files (.avsc)