FROM openjdk:17-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/Grocery-Mart-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
RUN ["java", "-jar", "/app.jar"]