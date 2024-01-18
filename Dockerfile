FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY . .

RUN apk add maven

RUN mvn clean package

# ARG JAR_FILE=target/*.jar

# COPY ${JAR_FILE} /app/application.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/target/application.jar"]