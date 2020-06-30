#!/bin/bash

OLD_PWD=$(pwd)
cd $(dirname $0)
cd docker
docker-compose down

cd "$OLD_PWD"