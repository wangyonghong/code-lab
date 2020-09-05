#!/bin/bash

set -eux

OLD_PWD=$(pwd)
cd $(dirname $0)

VERSION=$(./gradlew printVersion -q)
echo "$VERSION"

./gradlew bootJar
cp "./build/libs/spring-distributed-session-redis-$VERSION.jar" "./docker/app.jar"

cd docker

# 前台启动
docker-compose up --build

# 后台启动
# docker-compose up -d --build

cd "$OLD_PWD"
