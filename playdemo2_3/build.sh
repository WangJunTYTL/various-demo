#!/bin/sh

rm -rf lib/ target/
mvn play2:copy-dependencies
sbt compile
mvn resources:resources play2:package-war

#mvn play2:clean play2:copy-dependencies play2:compile
#
#play stage

######################
# window执行
# rmdir /s /q lib &&  rmdir /s /q lib targer && mvn play2:copy-dependencies && sbt compile && mvn resources:resources play2:package-war