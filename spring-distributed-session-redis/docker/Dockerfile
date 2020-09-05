FROM openjdk:11.0-jre
# https://hub.docker.com/_/openjdk

ENV SPRING_PROFILE prod

COPY ./app.jar /app.jar

CMD java -jar -Dspring.profiles.active=${SPRING_PROFILE} /app.jar