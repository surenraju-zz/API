FROM openjdk:8-jre-alpine
MAINTAINER suren.1988@gmail.com
WORKDIR /app
ADD target/*.jar app.jar
CMD ["java","-Dspring.profiles.active=docker","-jar","app.jar"]