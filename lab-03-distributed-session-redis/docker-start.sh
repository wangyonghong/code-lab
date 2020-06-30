#!/bin/bash

OLD_PWD=$(pwd)
cd $(dirname $0)
cd ..

VERSION=$(./gradlew :lab-03-distributed-session-redis:printVersion -q)
echo "$VERSION"
./gradlew :lab-03-distributed-session-redis:bootJar
cd lab-03-distributed-session-redis
cp "./build/libs/lab-03-distributed-session-redis-$VERSION.jar" "./docker/app.jar"
cd docker

# 前台启动
docker-compose up --build

# 后台启动
# docker-compose up -d --build

cd "$OLD_PWD"
