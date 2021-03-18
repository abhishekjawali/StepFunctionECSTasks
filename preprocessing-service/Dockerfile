FROM maven:3.6.3-amazoncorretto-11 AS build
WORKDIR /home/preprocessing-service
COPY . /home/preprocessing-service
RUN mvn -f /home/preprocessing-service/pom.xml clean package -DskipTests

FROM amazoncorretto:11-alpine-jdk
VOLUME /app
COPY --from=build /home/preprocessing-service/target/*.jar preprocessing-service.jar
ENV BUCKET_NAME=$BUCKET_NAME
ENTRYPOINT ["java","-jar","preprocessing-service.jar"]