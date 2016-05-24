# Spring

spring是一个提高Java项目开发效率的一个框架，提供IOC容器功能和AOP编程功能的框架

## 主要对象

ApplicationContext：spring上下文，是BeanFactory和FactorBean的容器，多个Context支持继承关系
BeanFactory：bean的注册表，提供对bean的实例化，装配，生命周期管理和指定bean的name或type的检索
FactoryBean：继承该对象，自己在在getObject方法中实现对象的实例化，用于实现复杂的bean的实例化
BeanFactoryPostProcessor：用于bean定义的处理
BeanPostProcessor：用于对bean的实例化前和实例化后处理
RootBeanDefinition：定义一个bean，应该具备的属性，比如bean的类型、范围，可以通过该类实例化一个bean

## 启动过程

先去扫描所有定义的bean，一个bean的定义有：是否是单例，bean的类型 可以参考 RootBeanDefinition
 
## 重要方法

AbstractBeanFactory.doGetBean(...):bean的检索过程

    beanFactory会先去自己单例bean的容器去检索是否有
    如果没有查到，回到父亲的beanFactory中检索
    如果还没有找到，则去创建
AbstractAutowireCapableBeanFactory.createBean(...):bean的创建过程

AbstractAutowireCapableBeanFactory.resolveBeforeInstantiation(...):AOP的实现过程

# Mybatis

mybatis是一个半自动的orm框架

## 主要对象

Configuration:
SqlSessionFactory:
