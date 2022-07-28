FROM openjdk:11.0-jdk-slim
COPY . .
EXPOSE 8082
CMD ["java", "-jar","/build/libs/auth-api-0.0.1-SNAPSHOT.jar"]