#!/bin/bash

OLD_PWD=$(pwd)
cd $(dirname $0)
cd docker
docker-compose down
rm -rf ./data/*

cd "$OLD_PWD"