# tron-rosetta-api
A rosetta api for java-tron.

##  Overview
In this article, we will illustrate how to build and use a Tron node supporting Rosetta blockchain specifications. We hope this set of specifications will make our TRON API architecture more standard and flexible

## Before Deployment
Please make sure you have installed the latest version of docker before deployment
Execute the following command to download tron-rosetta-api:

```
$ git clone https://github.com/tronprotocol/tron-rosetta-api.git
```

Compile docker image and use MainNet configuration for the image by default:
```
$ cd tron-rosetta-api
$ docker build -t tron-roseta-server
```


## Node Deployment

Start in Docker
```
$ docker run --name tron-rosetta -d -p 8080:8080 tron-roseta-server:latest
```

You can also customize a data directory
```
$ docker run --name tron-rosetta -d -p 8080:8080 -v ${realpath data}:/data/chain tron-roseta-server:latest
```



## API Reference

Based on Rosetta blockchain specifications, we have implemented the following interfaces:

#### Network
##### /network/list

###### Sample Request:
```
{
    "metadata": {
    }
}
```
###### Sample Response:
```
{
    "network_identifiers": [
         {
            "blockchain":"tron",
            "network":"mainnet"
        }
    ]
}
```


##### /network/options

###### Sample Request:
```
{
    "network_identifier": {
        "blockchain":"tron",
        "network":"mainnet"
    },
    "metadata": {
    }
}
```
###### Sample Response:
```
 {
    "version": {
        "rosetta_version":"1.4.0",
        "node_version":"4.0.0",
        "middleware_version":"1.0.2",
        "metadata":null
    },
    "allow": {
        "operation_statuses": [
             {
                "status":"SUCCESS",
                "successful":true
            },
             {
                "status":"REVERTED",
                "successful":false
            }
        ],
        "operation_types": [
            "TRANSFER"
        ],
        "errors": [
             {
                "code":12,
                "message":"Invalid account format",
                "retriable":true,
                "details":null
            },
             {
                "code":100,
                "message":"Invalid transaction format",
                "retriable":false,
                "details":null
            }
        ],
        "historical_balance_lookup":false
    }
}
```


##### /network/status

###### Sample Request:
```
{
    "network_identifier": {
        "blockchain":"tron",
        "network":"mainnet"
    },
    "metadata": {
    }
}
```
###### Sample Response:
```
  {
    "current_block_identifier": {
        "index":154,
        "hash":"000000000000009a4cefda5e311a42fd22038fb0178993e68daee29b0b3fdbe9"
    },
    "current_block_timestamp":1529891988000,
    "genesis_block_identifier": {
        "index":0,
        "hash":"00000000000000001ebf88508a03865c71d452e25f4d51194196a1d22b6653dc"
    },
    "oldest_block_identifier": {
        "index":154,
        "hash":"000000000000009a4cefda5e311a42fd22038fb0178993e68daee29b0b3fdbe9"
    },
    "peers": [
         {
            "peer_id":"d9ad9f981fa340b730b0af8fed18d4cec976af68c5458adbb01f2c38030d3590e96576751178ec0530abcde62920fe04533584571b6d2d5fee35be1486be4cac",
            "metadata": {
                "address":"127.0.0.1",
                "port":18888
            }
        },
         {
            "peer_id":"60f87819e8ae8fd9b89a9db197f953455b69f85d09438c34a02de4b05b2ff7bc591b491892a619e5a943dc867ee70efbff540ee7944a998f9dcac3cb9d98fac4",
            "metadata": {
                "address":"127.0.0.2",
                "port":18888
            }
        }
    ]
}
```
#### Acount
##### /account/balance

###### Sample Request:
```
{
    "network_identifier": {
        "blockchain": "tron",
        "network": "mainnet"
    },
    "account_identifier": {
        "address": "TRXnA3LdY5LqFatpLPpyYFYmKyJJCB3ZzR"
    }
}
```
###### Sample Response:
```
{
    "block_identifier": {
        "index": 72423,
        "hash": "0000000000011ae76ce0775746cd87f3b934324d5a29ffefc913319d66a02bd4"
    },
    "balances": [
        {
            "value": "986373520",
            "currency": {
                "symbol": "TRX",
                "decimals": 6
            }
        }
    ]
}
```
#### Block
##### /block

###### Sample Request:
```
{
    "network_identifier": {
        "blockchain": "tron",
        "network": "mainnet"
    },
    "block_identifier": {
        "index":1644001,
        "hash": "00000000001915e180e90b50e1ca88265cecc33d8582392c3a78170685b7a060"
    }
}
```
###### Sample Response:
```
{
    "block": {
        "block_identifier": {
            "index": 1644001,
            "hash": "00000000001915e180e90b50e1ca88265cecc33d8582392c3a78170685b7a060"
        },
        "parent_block_identifier": {
            "index": 1644000,
            "hash": "00000000001915e04b2def6ff986aecea81a792508f1c341a982f9eca1d90c2d"
        },
        "timestamp": 1534834407000,
        "transactions": [
            {
                "transaction_identifier": {
                    "hash": "a6865d2b059e2be293c49af8a45e64292b7315323873a66e2f68ccceec4baed5"
                },
                "operations": [
                    {
                        "operation_identifier": {
                            "index": 0
                        },
                        "type": "TransferContract",
                        "status": "SUCCESS"
                    }
                ]
            }
        ]
    }
}
```
##### /block/transaction
###### Sample Request:
```
{
    "network_identifier": {
        "blockchain": "tron",
        "network": "mainnet"
    },
    "block_identifier": {
        "index": 1402605,
        "hash": "00000000001566ed34ee7c02d076522b97bda6e4033aaeb1b8968e194d3ef0f1"
    },
    "transaction_identifier": {
        "hash": "7d5c61fc86fd6cb416f1675986b5c8b393586ad48fb57415d23b2b9cb465aabd"
    }
} 
```
###### Sample Response:
```
{
    "transaction": {
        "transaction_identifier": {
            "hash": "7d5c61fc86fd6cb416f1675986b5c8b393586ad48fb57415d23b2b9cb465aabd"
        },
        "operations": [
            {
                "operation_identifier": {
                    "index": 0
                },
                "type": "TransferAssetContract",
                "status": "SUCCESS"
            }
        ]
    }
}
```

#### Construction API
##### /construction/combine
###### Sample Request:
```
{
  "network_identifier": {
    "blockchain": "tron",
    "network": "mainnet"
  },
  "signatures": [
    {
      "hex_bytes": "c06c6fdec32480953e727b04915030ae85348a63cb75a48495eea8a3ba8a82723574fda3bc24310faab60508eb39e0b26a26843ae17c417ca68a189884c5b1e301",
      "public_key": {
        "curve_type": "secp256k1",
        "hex_bytes": "0483e4f38072fa59975fc796f220f4c07a7a6a3af1ad7fc091cbd6b8ebe78bac6a959da3587e6e761daf93693d4d2dc6b349fbc44dac5a9fcc5f809a59e93818ea"
      },
      "signature_type": "ecdsa",
      "signing_payload": {
        "address": "415624c12e308b03a1a6b21d9b86e3942fac1ab92b",
        "hex_bytes": "18015a45080112410a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412100a05121212a9cf1205232323a9cf180a709cb3dfd5b82e",
        "signature_type": "ecdsa"
      }
    }
  ],
  "unsigned_transaction": "0a5018015a45080112410a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412100a05121212a9cf1205232323a9cf180a709cb3dfd5b82e"
}
```
###### Sample Response:
```
{
  "signed_transaction": "0a5018015a45080112410a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412100a05121212a9cf1205232323a9cf180a709cb3dfd5b82e1241c06c6fdec32480953e727b04915030ae85348a63cb75a48495eea8a3ba8a82723574fda3bc24310faab60508eb39e0b26a26843ae17c417ca68a189884c5b1e301"
}
```

##### /construction/hash
###### Sample Request:
```
{
  "network_identifier": {
    "blockchain": "tron",
    "network": "mainnet"
  },
  "signed_transaction": "0a5018015a45080112410a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412100a05121212a9cf1205232323a9cf180a709cb3dfd5b82e1241c06c6fdec32480953e727b04915030ae85348a63cb75a48495eea8a3ba8a82723574fda3bc24310faab60508eb39e0b26a26843ae17c417ca68a189884c5b1e301"
}
```
###### Sample Response:
```
{
  "transaction_hash": "c82ddffde6f114b897405d9c49e42ee1804730d6b4200191d00cc32fd8aabc92"
}
```
##### /construction/submit
###### Sample Request:
```
{
  "network_identifier": {
    "blockchain": "tron",
    "network": "mainnet"
  },
  "signed_transaction": "0a5018015a45080112410a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412100a05121212a9cf1205232323a9cf180a709cb3dfd5b82e1241c06c6fdec32480953e727b04915030ae85348a63cb75a48495eea8a3ba8a82723574fda3bc24310faab60508eb39e0b26a26843ae17c417ca68a189884c5b1e301"
}
```
###### Sample Response:
```
{
  "transaction_identifier": {
    "hash": "c82ddffde6f114b897405d9c49e42ee1804730d6b4200191d00cc32fd8aabc92"
  },
  "metadata": null
}
```
##### /construction/parse
###### Sample Request:
```
{
  "network_identifier": {
    "blockchain": "tron",
    "network": "mainnet"
  },
  "signed": true,
  "transaction": "0a5018015a45080112410a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412100a05121212a9cf1205232323a9cf180a709cb3dfd5b82e1241c06c6fdec32480953e727b04915030ae85348a63cb75a48495eea8a3ba8a82723574fda3bc24310faab60508eb39e0b26a26843ae17c417ca68a189884c5b1e301"
}
```
###### Sample Response:
```
{
    "operations": [
        {
            "operation_identifier": {
                "index": 0
            },
            "type": "Transfer",
            "status": "Reverted"
        }
    ],
    "signers": [
        "415624c12e308b03a1a6b21d9b86e3942fac1ab92b"
    ]
}
```
###### /construction/derive
###### Sample Request:
```
{
    "network_identifier": {
        "blockchain": "tron",
        "network": "mainnet"
    },
    "public_key": {
        "hex_bytes": "04e3ef13a7b6a3ac44fe1dc278122174319214b3d5bea701c0ae97a6b5c63d376248742ce3fa2d73731a4785b5446a53b7f3d6997a1c7f14aebd8c74b987d76458",
        "curve_type": "secp256k1"
    }
}
```
###### Sample Response:
```
{
    "address": "TZJS19RBxK2TnfnF2gLheDL3U464meqwhi"
}
```

##### /construction/metadata

###### Sample Request:
```
{
    "network_identifier": {
        "blockchain": "tron",
        "network": "mainnet"
    },
    "options": {}
}
```
###### Sample Response:
```
{
    "metadata": {
        "expiration": 1530571284000,
        "reference_block_hash": "00000000015093a6efd83b53a0a5ee32c54b4bb71434855ea20bb1c5c31b3821",
        "reference_block_num": 22057894,
        "timestamp": 1596188062880
    }
}
```

/construction/payloads
###### Sample Request:
```
{
    "network_identifier": {
        "blockchain": "tron",
        "network": "mainnet"
    },
    "operations": [
        {
            "operation_identifier": {
                "index": 0
            },
            "type": "TransferContract",
            "status": "SUCCESS",
            "account": {
                "address": "TRXnA3LdY5LqFatpLPpyYFYmKyJJCB3ZzR"
            },
            "amount": {
                "value": "-3000000",
                "currency": {
                    "symbol": "TRX",
                    "decimals": 6
                }
            }
        },
        {
            "operation_identifier": {
                "index": 1
            },
            "type": "TransferContract",
            "status": "SUCCESS",
            "account": {
                "address": "TGo9Me13BSagSHXmKZDbZrLaFW9PXYYs3T"
            },
            "amount": {
                "value": "3000000",
                "currency": {
                    "symbol": "TRX",
                    "decimals": 6
                }
            }
        }
    ],
    "metadata": {
        "expiration": 1530571284000,
        "reference_block_hash": "00000000015093a6efd83b53a0a5ee32c54b4bb71434855ea20bb1c5c31b3821",
        "reference_block_num": 22057894,
        "timestamp": 1596188062880
    }
}
```
###### Sample Response:
```
{
    "unsigned_transaction": "0a86010a0207b52208a6b2effe4fc1656f408597d6a9c32c5a68080112640a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412330a1541aab2dfb2baeaf2dcaa757207ccdd6dc3e8dfb8db1215414ae1ad9344d1e393a4d733e03b2ec48f3909002e18c08db70170d988fba7c32c124137af6f9954cad06b1bb51d65408836184424d7c217834c2ca0734a6c7ba6298e7063beac7cef9cfb05467a981cda6534bcf8d0d6b0138447a9e763f409c75ba900",
    "payloads": [
        {
            "address": "TRXnA3LdY5LqFatpLPpyYFYmKyJJCB3ZzR",
            "hex_bytes": "0a0207b52208a6b2effe4fc1656f408597d6a9c32c5a68080112640a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412330a1541aab2dfb2baeaf2dcaa757207ccdd6dc3e8dfb8db1215414ae1ad9344d1e393a4d733e03b2ec48f3909002e18c08db70170d988fba7c32c"
        }
    ]
}
```