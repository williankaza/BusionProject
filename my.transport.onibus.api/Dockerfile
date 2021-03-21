FROM openjdk:8
ARG JAR_FILE=build/libs/my.transport.onibus.api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
COPY ${JAR_FILE} MyTransportOnibus-DockerImg.jar
ENTRYPOINT ["java","-jar","/MyTransportOnibus-DockerImg.jar"]