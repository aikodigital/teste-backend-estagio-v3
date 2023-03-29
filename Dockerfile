FROM openjdk:17-oracle
ADD target/aiko-api-docker.jar aiko-api-docker.jar
ENTRYPOINT ["java","-jar","/aiko-api-docker.jar"]
