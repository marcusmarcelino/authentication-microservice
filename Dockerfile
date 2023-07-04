FROM openjdk:17-alpine

WORKDIR /app

COPY . .

RUN apk add maven

RUN mvn clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/target/application.jar"]