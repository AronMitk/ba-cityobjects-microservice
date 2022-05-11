FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} landmark-detection.jar
ENTRYPOINT ["java","-jar","/landmark-detection.jar"]