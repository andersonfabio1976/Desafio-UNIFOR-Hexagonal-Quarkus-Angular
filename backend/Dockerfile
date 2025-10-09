# Etapa de Build
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml e resolve dependências
COPY pom.xml .
COPY core/pom.xml ./core/
COPY application/pom.xml ./application/
COPY adapters/pom.xml ./adapters/
COPY infrastructure/pom.xml ./infrastructure/
COPY . .

RUN mvn clean package -DskipTests

# Etapa de Runtime
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia o conteúdo gerado pelo Quarkus
COPY --from=build /app/infrastructure/target/quarkus-app /app/

# Define o comando de execução
ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]
