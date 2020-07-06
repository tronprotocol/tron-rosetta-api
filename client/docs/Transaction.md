

# Transaction

Transactions contain an array of Operations that are attributable to the same TransactionIdentifier.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transactionIdentifier** | [**TransactionIdentifier**](TransactionIdentifier.md) |  | 
**operations** | [**List&lt;Operation&gt;**](Operation.md) |  | 
**metadata** | [**Object**](.md) | Transactions that are related to other transactions (like a cross-shard transaction) should include the tranaction_identifier of these transactions in the metadata. |  [optional]



