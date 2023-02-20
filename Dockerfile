FROM openjdk:11 as build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} disneyapi.jar
ENTRYPOINT ["java","-jar","disneyapi.jar"]