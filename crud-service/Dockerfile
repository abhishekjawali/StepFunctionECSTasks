FROM maven:3.6.3-amazoncorretto-11 AS build
WORKDIR /home/crud-service
COPY . /home/crud-service
RUN mvn -f /home/crud-service/pom.xml clean package -DskipTests

FROM amazoncorretto:11-alpine-jdk
VOLUME /app
COPY --from=build /home/crud-service/target/*.jar crud-service.jar
ENV BUCKET_NAME=$BUCKET_NAME
ENTRYPOINT ["java","-jar","crud-service.jar"]