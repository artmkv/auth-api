FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon


FROM openjdk:11.0-jdk-slim
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/

EXPOSE 8081
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/auth-api-0.0.1-SNAPSHOT.jar"]


#FROM openjdk:11.0-jdk-slim
#COPY . .
#EXPOSE 8081
#CMD ["java", "-jar","/build/libs/auth-api-0.0.1-SNAPSHOT.jar"]