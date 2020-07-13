#!/bin/bash

OLD_PWD=$(pwd)
cd $(dirname $0)
cd ..

VERSION=$(./gradlew :lab-05-distributed-session-spring-security:printVersion -q)
echo "$VERSION"
./gradlew :lab-05-distributed-session-spring-security:bootJar
cd lab-05-distributed-session-spring-security
cp "./build/libs/lab-05-distributed-session-spring-security-$VERSION.jar" "./docker/app.jar"
cd docker

# 前台启动
rm -rf docker/data/*
docker-compose up --build

# 后台启动
# docker-compose up -d --build

cd "$OLD_PWD"
