

# ConstructionParseResponse

ConstructionParseResponse contains an array of operations that occur in a transaction blob. This should match the array of operations provided to `/construction/preprocess` and `/construction/payloads`.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**operations** | [**List&lt;Operation&gt;**](Operation.md) |  | 
**signers** | **List&lt;String&gt;** | All signers of a particular transaction. If the transaction is unsigned, it should be empty. | 
**metadata** | [**Object**](.md) |  |  [optional]



