FROM maven:3.6.1-jdk-8 AS BUILD_IMAGE
WORKDIR /usr/src/app
COPY api-init-service api-init-service
COPY api-service api-service
COPY pom.xml . 
RUN mvn clean install

FROM openjdk:8-jre-alpine
MAINTAINER suren.1988@gmail.com
WORKDIR /app
COPY --from=BUILD_IMAGE /usr/src/app/*.jar app.jar  
CMD ["java","-Dspring.profiles.active=docker","-jar","app.jar"]