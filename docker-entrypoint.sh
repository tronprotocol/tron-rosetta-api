#!/bin/bash
set -eo pipefail
shopt -s nullglob

if [[ "privatenet" == $NET_TYPE ]]; then
  java $JVM_OPTIONS -jar $JAR_NAME -d $DATA_DIR -c conf/privatenet.conf $TRON_OPTIONS
elif [[ "testnet" == $NET_TYPE ]]; then
  java $JVM_OPTIONS -jar $JAR_NAME -d $DATA_DIR -c conf/testnet.conf $TRON_OPTIONS
elif [[ "mainnet" == $NET_TYPE ]]; then
  java $JVM_OPTIONS -jar $JAR_NAME -d $DATA_DIR -c conf/mainnet.conf $TRON_OPTIONS
else
  echo "unknown net type" > logs/error.log
fi
