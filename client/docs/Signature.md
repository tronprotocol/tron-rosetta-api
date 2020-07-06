

# Signature

Signature contains the payload that was signed, the public keys of the keypairs used to produce the signature, the signature (encoded in hex), and the SignatureType. PublicKey is often times not known during construction of the signing payloads but may be needed to combine signatures properly.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**signingPayload** | [**SigningPayload**](SigningPayload.md) |  | 
**publicKey** | [**PublicKey**](PublicKey.md) |  | 
**signatureType** | [**SignatureType**](SignatureType.md) |  | 
**hexBytes** | **String** |  | 



