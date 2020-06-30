FROM openjdk:11.0-jre
# https://hub.docker.com/_/openjdk

COPY ./app.jar /app.jar

CMD java -jar -Dspring.profiles.active=docker /app.jar