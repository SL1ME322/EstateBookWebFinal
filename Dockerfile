# Стадия сборки
FROM ubuntu:latest AS build

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean

WORKDIR /app
COPY . .

# Запуск сборки проекта с использованием Gradle wrapper
RUN ./gradlew bootJar --no-daemon

# Стадия запуска
FROM openjdk:17-jdk-slim

WORKDIR /app

# Копирование собранного JAR файла из стадии сборки
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]