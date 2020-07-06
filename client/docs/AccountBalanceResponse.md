

# AccountBalanceResponse

An AccountBalanceResponse is returned on the /account/balance endpoint. If an account has a balance for each AccountIdentifier describing it (ex: an ERC-20 token balance on a few smart contracts), an account balance request must be made with each AccountIdentifier.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**blockIdentifier** | [**BlockIdentifier**](BlockIdentifier.md) |  | 
**balances** | [**List&lt;Amount&gt;**](Amount.md) | A single account may have a balance in multiple currencies. | 
**metadata** | [**Object**](.md) | Account-based blockchains that utilize a nonce or sequence number should include that number in the metadata. This number could be unique to the identifier or global across the account address. |  [optional]



