FROM maven:3.6.3-amazoncorretto-11 AS build
WORKDIR /home/pre-processing-service
COPY . /home/pre-processing-service
RUN mvn -f /home/pre-processing-service/pom.xml clean package -DskipTests

FROM amazoncorretto:11-alpine-jdk
VOLUME /app
COPY --from=build /home/pre-processing-service/target/*.jar pre-processing-service.jar
ENV BUCKET_NAME=$BUCKET_NAME
ENTRYPOINT ["java","-jar","pre-processing-service.jar"]