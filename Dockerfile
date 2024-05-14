# Используем образ с OpenJDK 11
FROM adoptopenjdk/openjdk11:alpine-slim

# Метаданные автора
LABEL authors="Honor"

# Установка временной зоны
RUN apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/Europe/Moscow /etc/localtime && \
    echo "Europe/Moscow" >  /etc/timezone && \
    apk del tzdata

# Создание директории приложения
RUN mkdir /app

# Копирование JAR файла в директорию приложения
COPY EstateBookWeb-main/target/Estate_Book.jar /app/Estate_Book.jar

# Указание рабочей директории
WORKDIR /app

# Определение порта, на котором будет работать ваше приложение
EXPOSE 8080

# Команда для запуска приложения
CMD ["java", "-jar", "Estate_Book.jar"]
