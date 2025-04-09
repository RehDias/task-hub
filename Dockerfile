FROM eclipse-temurin:17-jdk-jammy as build-image
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY ./src/main/ ./src/main/

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

COPY --from=build-image /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]