#!/bin/bash
set -eo pipefail
shopt -s nullglob

if [ "private" -eq $NET_TYPE ]
then
  java -jar $JARNAME -d chain -c conf/privatenet.conf
elif [ "testnet" -eq $NET_TYPE ]
  java -jar $JARNAME -d chain -c conf/testnet.conf
elif [ "mainnet" -eq $NET_TYPE ]
  java -jar $JARNAME -d chain -c conf/mainnet.conf
else
  echo "unknown net type" > error.log
fi