#!/bin/bash
set -e

host="unifor-mysql"
port="3306"
timeout=10
attempt=1

echo "--------------------------------------------------------"
echo "⏳ Iniciando o serviço de espera pelo MySQL..."
echo "Host: ${host}:${port}"
echo "--------------------------------------------------------"

# Loop de tentativas: usa 'nc -z' (Netcat) para verificar se a porta está aberta.
while ! nc -z $host $port; do
  echo "⚠️ Tentativa $attempt: MySQL em $host:$port ainda indisponível."
  echo "    Aguardando ${timeout} segundos..."

  sleep $timeout
  attempt=$((attempt+1))
done

echo ""
echo "--------------------------------------------------------"
echo "✅ MySQL está pronto e aceitando conexões! (Tentativas: $attempt)"
echo "--------------------------------------------------------"
exec "$@"