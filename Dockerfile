FROM maven:3.9-amazoncorretto-19 AS build
COPY . .
RUN mvn clean package -DskipTests

#DATA
COPY data/data.sql /docker-entrypoint-initdb.d/

ENV POSTGRES_HOST=${DB_URL}
ENV POSTGRES_PORT=5432
ENV POSTGRES_DB=test
ENV POSTGRES_USER=${DB_USER}
ENV POSTGRES_PASSWORD=${DB_PASSWORD}
#
# Package stage
#
FROM openjdk:19-jdk-alpine
COPY --from=build /target/*.war teste-backend-v3.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","teste-backend-v3.jar", "--spring.profiles.active=prod"]