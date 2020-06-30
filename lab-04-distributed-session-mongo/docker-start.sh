#!/bin/bash

OLD_PWD=$(pwd)
cd $(dirname $0)
cd ..

VERSION=$(./gradlew :lab-04-distributed-session-mongo:printVersion -q)
echo "$VERSION"
./gradlew :lab-04-distributed-session-mongo:bootJar
cd lab-04-distributed-session-mongo
cp "./build/libs/lab-04-distributed-session-mongo-$VERSION.jar" "./docker/app.jar"
cd docker

# 前台启动
rm -rf docker/data/*
docker-compose up --build

# 后台启动
# docker-compose up -d --build

cd "$OLD_PWD"
