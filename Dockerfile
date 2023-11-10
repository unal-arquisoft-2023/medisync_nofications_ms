FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
WORKDIR /build
COPY pom.xml .

# go-offline using the pom.xml
RUN mvn dependency:go-offline

# copy your other files
COPY ./src ./src

# compile the source code and package it in a jar file
RUN mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-alpine AS notification_ms
COPY --from=build /build/target/*.jar spring-notification-service.jar
ENTRYPOINT ["java","-jar","spring-notification-service.jar"]