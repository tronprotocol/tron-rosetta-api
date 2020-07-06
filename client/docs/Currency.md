

# Currency

Currency is composed of a canonical Symbol and Decimals. This Decimals value is used to convert an Amount.Value from atomic units (Satoshis) to standard units (Bitcoins).
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**symbol** | **String** | Canonical symbol associated with a currency. | 
**decimals** | **Integer** | Number of decimal places in the standard unit representation of the amount. For example, BTC has 8 decimals. Note that it is not possible to represent the value of some currency in atomic units that is not base 10. | 
**metadata** | [**Object**](.md) | Any additional information related to the currency itself. For example, it would be useful to populate this object with the contract address of an ERC-20 token. |  [optional]



