# Etapa de Build
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml ./
COPY domain/pom.xml ./domain/
COPY application/pom.xml ./application/
COPY infrastructure/pom.xml ./infrastructure/
COPY adapters/pom.xml ./adapters/

COPY . .

RUN mvn clean package -DskipTests

# Etapa de Runtime
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/adapters/target/quarkus-app /app/quarkus-app

EXPOSE 8080

# ✅ Forma correta de rodar o Quarkus
ENTRYPOINT ["java", "-jar", "/app/quarkus-app/quarkus-run.jar"]
