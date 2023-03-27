FROM maven:3.9-amazoncorretto-19 AS build
COPY . .
RUN mvn clean package -DskipTests

#DATA
COPY data/data.sql /docker-entrypoint-initdb.d/

# Package stage
#
FROM openjdk:19-jdk-alpine
COPY --from=build /target/*.war teste-backend-v3.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","teste-backend-v3.jar", "--spring.profiles.active=prod"]