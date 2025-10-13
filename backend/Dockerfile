# Etapa de Build
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

COPY backend/pom.xml ./
COPY backend/domain/pom.xml ./domain/
COPY backend/application/pom.xml ./application/
COPY backend/infrastructure/pom.xml ./infrastructure/
COPY backend/adapters/pom.xml ./adapters/

COPY . .

RUN mvn clean package -DskipTests

# Etapa de Runtime
FROM eclipse-temurin:17-jre


COPY --from=build /app/backend/adapters/target/quarkus-app /app/quarkus-app

# Copia os dois scripts de wait
COPY docker/wait-for-mysql.sh /app/wait-for-mysql.sh
COPY docker/wait-for-keycloak.sh /app/wait-for-keycloak.sh

RUN chmod +x /app/wait-for-mysql.sh /app/wait-for-keycloak.sh


EXPOSE 8080

# ✅ Forma correta de rodar o Quarkus
ENTRYPOINT ["bash", "-c", "/app/wait-for-mysql.sh && /app/wait-for-keycloak.sh && java -jar /app/quarkus-app/quarkus-run.jar"]