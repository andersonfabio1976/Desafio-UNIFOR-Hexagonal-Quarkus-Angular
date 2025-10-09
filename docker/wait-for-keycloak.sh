#!/bin/bash
set -e

host="unifor-keycloak"
port="8080"
# Endpoint de saúde do Keycloak (mais estável para verificar se o serviço está vivo)
url="http://${host}:${port}/health/ready"
timeout=10
attempt=1

echo "--------------------------------------------------------"
echo "⏳ Iniciando o serviço de espera pelo Keycloak..."
echo "Host: ${host}:${port}"
echo "--------------------------------------------------------"

# Loop de tentativas
while ! curl --output /dev/null --silent --head --fail "$url"; do
  # Se o curl falhar (ou seja, o Keycloak ainda não respondeu 'OK')
  echo "⚠️ Tentativa $attempt: Keycloak em $url ainda indisponível."
  echo "    Logando uma exception simulada e aguardando ${timeout} segundos..."

  sleep $timeout
  attempt=$((attempt+1))
done

# Quando o loop for interrompido, significa que o curl foi bem-sucedido
echo ""
echo "--------------------------------------------------------"
echo "✅ Keycloak está pronto! (Chegou na Tentativa: $attempt)"
echo "--------------------------------------------------------"
exec "$@"