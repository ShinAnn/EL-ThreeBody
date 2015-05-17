#!/bin/sh

# define constants
PROJECT_PATH=/root/EL
JAR_PATH=$PROJECT_PATH/lib
BIN_PATH=$PROJECT_PATH/bin
SRC_PATH=$PROJECT_PATH/src

#
rm -rf $BIN_PATH
mkdir $BIN_PATH

#
javac -d $BIN_PATH -classpath $JAR_PATH/mysql-connector-java-5.1.35-bin.jar:$JAR_PATH/dom4j-1.6.1.jar:$JAR_PATH/saint.jar @list.txt