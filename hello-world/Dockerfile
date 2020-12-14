FROM adoptopenjdk:11-jre-hotspot

ENV DB_USER=erkanDocker
ARG JAR_FILE=target/hello-world-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 5000

ENTRYPOINT ["java","-jar","/app.jar"]