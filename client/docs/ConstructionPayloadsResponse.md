

# ConstructionPayloadsResponse

ConstructionTransactionResponse is returned by `/construction/payloads`. It contains an unsigned transaction blob (that is usually needed to construct the a network transaction from a collection of signatures) and an array of payloads that must be signed by the caller.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**unsignedTransaction** | **String** |  | 
**payloads** | [**List&lt;SigningPayload&gt;**](SigningPayload.md) |  | 



