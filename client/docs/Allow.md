

# Allow

Allow specifies supported Operation status, Operation types, and all possible error statuses. This Allow object is used by clients to validate the correctness of a Rosetta Server implementation. It is expected that these clients will error if they receive some response that contains any of the above information that is not specified here.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**operationStatuses** | [**List&lt;OperationStatus&gt;**](OperationStatus.md) | All Operation.Status this implementation supports. Any status that is returned during parsing that is not listed here will cause client validation to error. | 
**operationTypes** | **List&lt;String&gt;** | All Operation.Type this implementation supports. Any type that is returned during parsing that is not listed here will cause client validation to error. | 
**errors** | [**List&lt;Error&gt;**](Error.md) | All Errors that this implementation could return. Any error that is returned during parsing that is not listed here will cause client validation to error. | 
**historicalBalanceLookup** | **Boolean** | Any Rosetta implementation that supports querying the balance of an account at any height in the past should set this to true. | 



