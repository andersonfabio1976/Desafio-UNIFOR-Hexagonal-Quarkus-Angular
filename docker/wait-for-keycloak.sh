#!/bin/bash
set -e

host="unifor-keycloak"
port="8080"
health_url="http://${host}:${port}/health/ready"
realm_url="http://${host}:${port}/realms/unifor-realm/.well-known/openid-configuration"

echo "--------------------------------------------------------"
echo "⏳ Aguardando Keycloak iniciar (${health_url})..."
echo "--------------------------------------------------------"

# Espera até o /health/ready responder 200
until [[ "$(curl -s -o /dev/null -w '%{http_code}' $health_url)" == "200" ]]; do
  echo "🔁 Keycloak ainda não disponível (healthcheck)... aguardando 5s"
  sleep 5
done

echo "✅ Keycloak respondeu no health endpoint. Verificando realm 'unifor'..."
echo "--------------------------------------------------------"

# Aguarda o realm ficar disponível com timeout (máx. 120s)
max_attempts=40
attempt=1

while true; do
  status=$(curl -s -o /dev/null -w '%{http_code}' "$realm_url" || echo "000")
  if [ "$status" == "200" ]; then
    # Verifica se o JSON contém o campo issuer
    if curl -s "$realm_url" | grep -q '"issuer"'; then
      echo "✅ Realm 'unifor' está disponível e ativo!"
      echo "--------------------------------------------------------"
      break
    fi
  fi

  echo "🔁 [Tentativa $attempt/$max_attempts] Realm 'unifor' ainda não disponível (HTTP $status)... aguardando 5s"
  sleep 5
  attempt=$((attempt+1))

  if [ "$attempt" -gt "$max_attempts" ]; then
    echo "❌ Timeout: Realm 'unifor' não ficou disponível após $((max_attempts*5)) segundos."
    echo "Verifique se o import do realm está correto no diretório ./keycloak/import/"
    exit 1
  fi
done

exec "$@"
