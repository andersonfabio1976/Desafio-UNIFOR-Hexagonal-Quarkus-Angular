# Etapa de Build
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copia os arquivos pom.xml para aproveitar o cache
COPY pom.xml .
COPY core/pom.xml ./core/
COPY application/pom.xml ./application/
COPY adapters/pom.xml ./adapters/
COPY infrastructure/pom.xml ./infrastructure/

# Copia todo o código-fonte
COPY . .

# Executa o build do Quarkus (ignorando os testes para agilizar)
RUN mvn clean package -DskipTests

# Etapa de Runtime
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copia o aplicativo empacotado da etapa de build
COPY --from=build /app/adapters/target/quarkus-app /app


# Expõe a porta do Quarkus
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "quarkus-run.jar"]
