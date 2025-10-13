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

while ! (echo > /dev/tcp/$host/$port) >/dev/null 2>&1; do
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
