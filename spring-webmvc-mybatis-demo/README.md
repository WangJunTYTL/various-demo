# Spring

spring是一个提高Java项目开发效率的一个框架，提供IOC容器功能和AOP编程功能的框架

## 主要对象

ApplicationContext：spring上下文，是BeanFactory和FactorBean的容器，多个Context支持继承关系
BeanFactory：bean的注册表，提供对bean的实例化，装配，生命周期挂了和指定bean的name或type的查找

## 重要方法

AbstractBeanFactory.doGetBean(...):bean的检索过程
AbstractAutowireCapableBeanFactory.createBean(...):bean的创建过程
AbstractAutowireCapableBeanFactory.resolveBeforeInstantiation(...):AOP的实现过程

# Mybatis

mybatis是一个半自动的orm框架

## 主要对象

Configuration:
SqlSessionFactory:
