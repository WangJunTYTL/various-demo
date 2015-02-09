读取配置文件，支持properties conf json 格式文件
==================

### The convenience method ConfigFactory.load() loads the following (first-listed are higher priority):

1. system properties
1. application.conf (all resources on classpath with this name)
1. application.json (all resources on classpath with this name)
1. application.properties (all resources on classpath with this name)
1. reference.conf (all resources on classpath with this name)

### If you have trouble with your configuration, some useful tips.
+ Set the Java system property -Dconfig.trace=loads to get output on stderr describing each file that is loaded. Note: this feature is not included in the older version in Play/Akka 2.0.
+ Use myConfig.root().render() to get a Config printed out as a string with comments showing where each value came from.