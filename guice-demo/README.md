# Guice

google 公司的IOC容器，仅仅是一款小巧的容器

## 注入方式

1. 在Module中明确指定
1. 在接口中利用@ImplementedBy指定实现类
1. 在接口中利用@ProvideBy指定获取实例的方式

## scope

默认是每次getInstance都会实例化一个对象，如果需要更改为单列，需要明确指定
