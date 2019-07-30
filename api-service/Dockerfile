FROM maven:3.6.1-jdk-8 AS build
COPY api-init-service /usr/src/app/api-init-service 
COPY api-service /usr/src/app/api-service
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean install

FROM openjdk:8-jre-alpine
MAINTAINER suren.1988@gmail.com
WORKDIR /app
COPY --from=build /usr/src/app/*.jar app.jar  
CMD ["java","-Dspring.profiles.active=docker","-jar","app.jar"]