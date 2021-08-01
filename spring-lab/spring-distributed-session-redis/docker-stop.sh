#!/bin/bash

set -eux

OLD_PWD=$(pwd)
cd $(dirname $0)
cd docker
docker-compose down
rm -rf ./data/*
rm -f ./app.jar

cd "$OLD_PWD"
