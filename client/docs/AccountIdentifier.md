

# AccountIdentifier

The account_identifier uniquely identifies an account within a network. All fields in the account_identifier are utilized to determine this uniqueness (including the metadata field, if populated).
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**address** | **String** | The address may be a cryptographic public key (or some encoding of it) or a provided username. | 
**subAccount** | [**SubAccountIdentifier**](SubAccountIdentifier.md) |  |  [optional]
**metadata** | [**Object**](.md) | Blockchains that utilize a username model (where the address is not a derivative of a cryptographic public key) should specify the public key(s) owned by the address in metadata. |  [optional]



