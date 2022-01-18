FROM gradle:jdk17-alpine AS cache
WORKDIR /app
ENV GRADLE_USER_HOME /cache
COPY settings.gradle.kts ./
COPY build.gradle.kts ./
RUN gradle build --build-cache -x bootJar --info

FROM gradle:jdk17-alpine as build
WORKDIR /app
COPY --from=cache /cache /home/gradle/.gradle
COPY settings.gradle.kts .
COPY build.gradle.kts .
COPY src ./src
RUN gradle clean build --build-cache --info

FROM openjdk:17-jdk-alpine as run
COPY --from=build /app/build/libs/training-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]