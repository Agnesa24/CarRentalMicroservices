# Build from the repository root so Gradle can see settings.gradle and sibling modules
#   docker build -f rental-service/Dockerfile .
FROM gradle:8-jdk17 AS builder
WORKDIR /app

COPY gradlew ./
COPY gradlew.bat ./
COPY gradle/ gradle/
COPY settings.gradle ./
COPY vehicle-service/ vehicle-service/
COPY customer-service/ customer-service/
COPY billing-service/ billing-service/
COPY rental-service/ rental-service/

RUN chmod +x gradlew
RUN ./gradlew :rental-service:bootJar -x test --no-daemon

FROM amazoncorretto:17
WORKDIR /app
COPY --from=builder /app/rental-service/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
