

# Version

The Version object is utilized to inform the client of the versions of different components of the Rosetta implementation.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**rosettaVersion** | **String** | The rosetta_version is the version of the Rosetta interface the implementation adheres to. This can be useful for clients looking to reliably parse responses. | 
**nodeVersion** | **String** | The node_version is the canonical version of the node runtime. This can help clients manage deployments. | 
**middlewareVersion** | **String** | When a middleware server is used to adhere to the Rosetta interface, it should return its version here. This can help clients manage deployments. |  [optional]
**metadata** | [**Object**](.md) | Any other information that may be useful about versioning of dependent services should be returned here. |  [optional]



