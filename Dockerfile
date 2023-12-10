FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar .
CMD ["java", "-jar", "Grocery-Mart-0.0.1-SNAPSHOT.jar"]