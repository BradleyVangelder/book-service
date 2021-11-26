FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY target/bookservice-0.1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
