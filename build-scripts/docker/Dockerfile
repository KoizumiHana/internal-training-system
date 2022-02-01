FROM openjdk:17-jdk-alpine

RUN apk --no-cache add curl

ARG JAR_FOLDER
WORKDIR /opt

COPY ${JAR_FOLDER}/spring-boot-loader/ ./
COPY ${JAR_FOLDER}/dependencies/ ./
COPY ${JAR_FOLDER}/snapshot-dependencies/ ./
COPY ${JAR_FOLDER}/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]