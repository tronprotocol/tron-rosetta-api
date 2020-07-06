

# Error

Instead of utilizing HTTP status codes to describe node errors (which often do not have a good analog), rich errors are returned using this object.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**code** | **Integer** | Code is a network-specific error code. If desired, this code can be equivalent to an HTTP status code. | 
**message** | **String** | Message is a network-specific error message. | 
**retriable** | **Boolean** | An error is retriable if the same request may succeed if submitted again. | 
**details** | [**Object**](.md) | Often times it is useful to return context specific to the request that caused the error (i.e. a sample of the stack trace or impacted account) in addition to the standard error message. |  [optional]



