FROM gradle:4.2.1-jdk8-alpine as builder
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build

FROM openjdk:8
COPY --from=builder /app/build/libs/log-handler-1.0.0.jar /app/
WORKDIR /app
EXPOSE 8084
ENTRYPOINT [ "java", "-jar", "-Xms32m", "-Xmx128m", "log-handler-1.0.0.jar"]