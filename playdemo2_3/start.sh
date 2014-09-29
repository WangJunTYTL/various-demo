#!/bin/sh
mvn play2:clean play2:copy-dependencies
play compile
mvn compile
play ~run

####### win 下执行命令
#mvn play2:clean play2:copy-dependencies && play compile && mvn compile
