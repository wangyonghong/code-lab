# lab-05-distributed-session-spring-security

## start

```shell
# 只启动 Redis
cd docker
docker-compose up redis
```

```shell
./docker-start.sh
```

## stop

```shell
./docker-stop.sh
```

## 访问浏览器

http://localhost:8080/

http://localhost:8080/session/list?username=user

## 一点说明

当使用 spring-session-redis 时，需要开启 Redis 的过期事件通知。
否则过期的session不能被删除

```
config get notify-keyspace-events
config set notify-keyspace-events gxE
```