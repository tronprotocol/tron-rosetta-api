

# AccountBalanceRequest

An AccountBalanceRequest is utilized to make a balance request on the /account/balance endpoint. If the block_identifier is populated, a historical balance query should be performed.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**networkIdentifier** | [**NetworkIdentifier**](NetworkIdentifier.md) |  | 
**accountIdentifier** | [**AccountIdentifier**](AccountIdentifier.md) |  | 
**blockIdentifier** | [**PartialBlockIdentifier**](PartialBlockIdentifier.md) |  |  [optional]



