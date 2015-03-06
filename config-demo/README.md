## 读取配置文件，支持properties conf json 格式文件
==================

The configuration library is really powerful, explaining all features exceeds the scope affordable here. In particular not covered are how to include other configuration files within other files (see a small example at Including files) and copying parts of the configuration tree by way of path substitutions.

### The convenience method ConfigFactory.load() loads the following (first-listed are higher priority):

1. system properties
1. application.conf (all resources on classpath with this name)
1. application.json (all resources on classpath with this name)
1. application.properties (all resources on classpath with this name)
1. reference.conf (all resources on classpath with this name)

### If you have trouble with your configuration, some useful tips.
+ Set the Java system property -Dconfig.trace=loads to get output on stderr describing each file that is loaded. Note: this feature is not included in the older version in Play/Akka 2.0.
+ Use myConfig.root().render() to get a Config printed out as a string with comments showing where each value came from.

### Merging config trees

Any two Config objects can be merged with an associative operation called withFallback, like merged = firstConfig.withFallback(secondConfig).

The withFallback operation is used inside the library to merge duplicate keys in the same file and to merge multiple files. ConfigFactory.load() uses it to stack system properties over application.conf over reference.conf.

You can also use withFallback to merge in some hardcoded values, or to "lift" a subtree up to the root of the configuration; say you have something like:

foo=42
dev.foo=57
prod.foo=10
Then you could code something like:

Config devConfig = originalConfig
                     .getConfig("dev")
                     .withFallback(originalConfig)
There are lots of ways to use withFallback.

