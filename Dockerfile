FROM openjdk:17-oracle
COPY target/*.jar spring-notification-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-notification-service.jar"]