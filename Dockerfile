# Dockerfile for building the project
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Dockerfile for running the application
FROM openjdk:17-oracle AS runtime

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

