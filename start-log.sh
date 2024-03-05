#!/bin/bash

docker compose logs -f api01 > ./logs/api01.log 2>&1 &
docker compose logs -f api02 > ./logs/api02.log 2>&1 &

# Salva os logs do container api01 em um arquivo
# docker logs api01 > logs/api01_logs.log

# Salva os logs do container api02 em um arquivo
# docker logs api02 > logs/api02_logs.log

./executar-teste-local.sh
