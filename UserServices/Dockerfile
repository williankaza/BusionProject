FROM openjdk:8
ARG JAR_FILE=build/libs/UserServices-0.0.1-SNAPSHOT.jar
EXPOSE 8080
COPY ${JAR_FILE} UserServices-DockerImg.jar
COPY ${DEPENDENCY} build/resources/main/mytransport-fiap36scj.json src/main/resources/
ENTRYPOINT ["java","-jar","/UserServices-DockerImg.jar"]