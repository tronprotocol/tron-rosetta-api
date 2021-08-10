#!/bin/bash
set -eo pipefail
shopt -s nullglob

CONFIG_FILE="conf/mainnet.conf"

if [[ "privatenet" == $NET_TYPE ]]; then
  CONFIG_FILE="conf/privatenet.conf"
elif [[ "testnet" == $NET_TYPE ]]; then
  CONFIG_FILE="conf/testnet.conf"
elif [[ "mainnet" == $NET_TYPE ]]; then
  CONFIG_FILE="conf/mainnet.conf"
else
  echo "unknown net type" > logs/error.log
fi

if [[ "offline" == $RUN_MODE ]]; then
  CONFIG_FILE="conf/offline.conf"
fi

exec java $JVM_OPTIONS -jar $JAR_NAME -d $DATA_DIR -c $CONFIG_FILE $TRON_OPTIONS
