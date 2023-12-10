FROM maven:3.9.5-eclipse-temurin-17-alpine AS maven-build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests=true

FROM openjdk:17-jdk-alpine
WORKDIR /app
EXPOSE 8080
COPY --from=maven-build /app/target/*.jar .
CMD ["java", "-jar", "Grocery-Mart-0.0.1-SNAPSHOT.jar"]