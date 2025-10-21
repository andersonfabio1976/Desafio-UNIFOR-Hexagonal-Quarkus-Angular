#!/bin/sh
echo "⏳ Aguardando Keycloak responder em http://keycloak:8080/realms/unifor-realm ..."

until curl -s http://keycloak:8080/realms/unifor-realm > /dev/null; do
  echo "🚧 Keycloak ainda não está pronto. Tentando novamente em 10s..."
  sleep 10
done

echo "✅ Keycloak disponível! Iniciando o Quarkus..."
exec java -jar /app/quarkus-app/quarkus-run.jar
