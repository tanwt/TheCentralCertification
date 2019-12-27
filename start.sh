#!/bin/bash
path="target/"
fileName="certification"
version="0.0.1-SNAPSHOT"
environment="dev"
mvn clean && mvn package
if test $# -eq 1
then
	if test $1 = "product"
	then
		environment="product"
	fi
fi
echo "当前执行环境:$environment"
java -jar $path$fileName-$version.jar --spring.profiles.active=$environment
