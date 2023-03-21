FROM openjdk:17-jdk-alpine
EXPOSE 8080/tcp
VOLUME /tmp
ARG JAR_FILE=target/Accommodator-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

