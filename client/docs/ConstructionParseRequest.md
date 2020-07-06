

# ConstructionParseRequest

ConstructionParseRequest is the input to the `/construction/parse` endpoint. It allows the caller to parse either an unsigned or signed transaction.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**networkIdentifier** | [**NetworkIdentifier**](NetworkIdentifier.md) |  | 
**signed** | **Boolean** | Signed is a boolean indicating whether the transaction is signed. | 
**transaction** | **String** | This must be either the unsigned transaction blob returned by &#x60;/construction/payloads&#x60; or the signed transaction blob returned by &#x60;/construction/combine&#x60;. | 



