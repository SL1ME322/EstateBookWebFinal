# Стадия сборки
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Стадия запуска
FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY --from=build /app/EstateBookWeb-main/target/*.jar estatebookweb.jar

ENTRYPOINT ["java", "-jar", "estatebookweb.jar"]