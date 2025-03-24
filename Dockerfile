FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build.gradle .
COPY gradlew .
COPY gradlew.bat .
COPY settings.gradle .
# COPY gradle gradle
COPY src src
COPY .env .

RUN chmod +x gradlew
CMD ["./gradlew", "run"]
