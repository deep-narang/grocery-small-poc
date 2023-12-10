FROM openjdk:17-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
RUN ["java", "-jar", "/app.jar"]