

# SigningPayload

SigningPayload is signed by the client with the keypair associated with an address using the specified SignatureType. SignatureType can be optionally populated if there is a restriction on the signature scheme that can be used to sign the payload.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**address** | **String** | The network-specific address of the account that should sign the payload. | 
**hexBytes** | **String** |  | 
**signatureType** | [**SignatureType**](SignatureType.md) |  |  [optional]



