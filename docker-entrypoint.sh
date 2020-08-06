#!/bin/bash
set -eo pipefail
shopt -s nullglob

if [[ "private" == $NET_TYPE ]]; then
  java -jar $JARNAME -d chain -c conf/privatenet.conf -w
elif [[ "testnet" == $NET_TYPE ]]; then
  java -jar $JARNAME -d chain -c conf/shasta.conf
elif [[ "mainnet" == $NET_TYPE ]]; then
  java -jar $JARNAME -d chain -c conf/mainnet.conf
else
  echo "unknown net type" > error.log
fi