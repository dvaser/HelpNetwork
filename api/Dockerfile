FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR api
COPY --from=build target/*.jar api.jar
ENTRYPOINT ["java", "-jar", "api.jar"]