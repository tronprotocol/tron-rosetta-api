# ConstructionApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**constructionCombine**](ConstructionApi.md#constructionCombine) | **POST** /construction/combine | Create Network Transaction from Signatures
[**constructionDerive**](ConstructionApi.md#constructionDerive) | **POST** /construction/derive | Derive an Address from a PublicKey
[**constructionHash**](ConstructionApi.md#constructionHash) | **POST** /construction/hash | Get the Hash of a Signed Transaction
[**constructionMetadata**](ConstructionApi.md#constructionMetadata) | **POST** /construction/metadata | Get Metadata for Transaction Construction
[**constructionParse**](ConstructionApi.md#constructionParse) | **POST** /construction/parse | Parse a Transaction
[**constructionPayloads**](ConstructionApi.md#constructionPayloads) | **POST** /construction/payloads | Generate an Unsigned Transaction and Signing Payloads
[**constructionPreprocess**](ConstructionApi.md#constructionPreprocess) | **POST** /construction/preprocess | Create a Request to Fetch Metadata
[**constructionSubmit**](ConstructionApi.md#constructionSubmit) | **POST** /construction/submit | Submit a Signed Transaction


<a name="constructionCombine"></a>
# **constructionCombine**
> ConstructionCombineResponse constructionCombine(constructionCombineRequest)

Create Network Transaction from Signatures

Combine creates a network-specific transaction from an unsigned transaction and an array of provided signatures. The signed transaction returned from this method will be sent to the &#x60;/construction/submit&#x60; endpoint by the caller.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConstructionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConstructionApi apiInstance = new ConstructionApi(defaultClient);
    ConstructionCombineRequest constructionCombineRequest = new ConstructionCombineRequest(); // ConstructionCombineRequest | 
    try {
      ConstructionCombineResponse result = apiInstance.constructionCombine(constructionCombineRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConstructionApi#constructionCombine");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **constructionCombineRequest** | [**ConstructionCombineRequest**](ConstructionCombineRequest.md)|  |

### Return type

[**ConstructionCombineResponse**](ConstructionCombineResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Expected response to a valid request |  -  |
**0** | unexpected error |  -  |

<a name="constructionDerive"></a>
# **constructionDerive**
> ConstructionDeriveResponse constructionDerive(constructionDeriveRequest)

Derive an Address from a PublicKey

Derive returns the network-specific address associated with a public key. Blockchains that require an on-chain action to create an account should not implement this method.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConstructionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConstructionApi apiInstance = new ConstructionApi(defaultClient);
    ConstructionDeriveRequest constructionDeriveRequest = new ConstructionDeriveRequest(); // ConstructionDeriveRequest | 
    try {
      ConstructionDeriveResponse result = apiInstance.constructionDerive(constructionDeriveRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConstructionApi#constructionDerive");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **constructionDeriveRequest** | [**ConstructionDeriveRequest**](ConstructionDeriveRequest.md)|  |

### Return type

[**ConstructionDeriveResponse**](ConstructionDeriveResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Expected response to a valid request |  -  |
**0** | unexpected error |  -  |

<a name="constructionHash"></a>
# **constructionHash**
> ConstructionHashResponse constructionHash(constructionHashRequest)

Get the Hash of a Signed Transaction

TransactionHash returns the network-specific transaction hash for a signed transaction.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConstructionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConstructionApi apiInstance = new ConstructionApi(defaultClient);
    ConstructionHashRequest constructionHashRequest = new ConstructionHashRequest(); // ConstructionHashRequest | 
    try {
      ConstructionHashResponse result = apiInstance.constructionHash(constructionHashRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConstructionApi#constructionHash");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **constructionHashRequest** | [**ConstructionHashRequest**](ConstructionHashRequest.md)|  |

### Return type

[**ConstructionHashResponse**](ConstructionHashResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Expected response to a valid request |  -  |
**0** | unexpected error |  -  |

<a name="constructionMetadata"></a>
# **constructionMetadata**
> ConstructionMetadataResponse constructionMetadata(constructionMetadataRequest)

Get Metadata for Transaction Construction

Get any information required to construct a transaction for a specific network. Metadata returned here could be a recent hash to use, an account sequence number, or even arbitrary chain state. The request used when calling this endpoint is often created by calling &#x60;/construction/preprocess&#x60; in an offline environment. It is important to clarify that this endpoint should not pre-construct any transactions for the client (this should happen in &#x60;/construction/payloads&#x60;). This endpoint is left purposely unstructured because of the wide scope of metadata that could be required.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConstructionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConstructionApi apiInstance = new ConstructionApi(defaultClient);
    ConstructionMetadataRequest constructionMetadataRequest = new ConstructionMetadataRequest(); // ConstructionMetadataRequest | 
    try {
      ConstructionMetadataResponse result = apiInstance.constructionMetadata(constructionMetadataRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConstructionApi#constructionMetadata");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **constructionMetadataRequest** | [**ConstructionMetadataRequest**](ConstructionMetadataRequest.md)|  |

### Return type

[**ConstructionMetadataResponse**](ConstructionMetadataResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Expected response to a valid request |  -  |
**0** | unexpected error |  -  |

<a name="constructionParse"></a>
# **constructionParse**
> ConstructionParseResponse constructionParse(constructionParseRequest)

Parse a Transaction

Parse is called on both unsigned and signed transactions to understand the intent of the formulated transaction. This is run as a sanity check before signing (after &#x60;/construction/payloads&#x60;) and before broadcast (after &#x60;/construction/combine&#x60;). 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConstructionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConstructionApi apiInstance = new ConstructionApi(defaultClient);
    ConstructionParseRequest constructionParseRequest = new ConstructionParseRequest(); // ConstructionParseRequest | 
    try {
      ConstructionParseResponse result = apiInstance.constructionParse(constructionParseRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConstructionApi#constructionParse");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **constructionParseRequest** | [**ConstructionParseRequest**](ConstructionParseRequest.md)|  |

### Return type

[**ConstructionParseResponse**](ConstructionParseResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Expected response to a valid request |  -  |
**0** | unexpected error |  -  |

<a name="constructionPayloads"></a>
# **constructionPayloads**
> ConstructionPayloadsResponse constructionPayloads(constructionPayloadsRequest)

Generate an Unsigned Transaction and Signing Payloads

Payloads is called with an array of operations and the response from &#x60;/construction/metadata&#x60;. It returns an unsigned transaction blob and a collection of payloads that must be signed by particular addresses using a certain SignatureType. The array of operations provided in transaction construction often times can not specify all \&quot;effects\&quot; of a transaction (consider invoked transactions in Ethereum). However, they can deterministically specify the \&quot;intent\&quot; of the transaction, which is sufficient for construction. For this reason, parsing the corresponding transaction in the Data API (when it lands on chain) will contain a superset of whatever operations were provided during construction.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConstructionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConstructionApi apiInstance = new ConstructionApi(defaultClient);
    ConstructionPayloadsRequest constructionPayloadsRequest = new ConstructionPayloadsRequest(); // ConstructionPayloadsRequest | 
    try {
      ConstructionPayloadsResponse result = apiInstance.constructionPayloads(constructionPayloadsRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConstructionApi#constructionPayloads");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **constructionPayloadsRequest** | [**ConstructionPayloadsRequest**](ConstructionPayloadsRequest.md)|  |

### Return type

[**ConstructionPayloadsResponse**](ConstructionPayloadsResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Expected response to a valid request |  -  |
**0** | unexpected error |  -  |

<a name="constructionPreprocess"></a>
# **constructionPreprocess**
> ConstructionPreprocessResponse constructionPreprocess(constructionPreprocessRequest)

Create a Request to Fetch Metadata

Preprocess is called prior to &#x60;/construction/payloads&#x60; to construct a request for any metadata that is needed for transaction construction given (i.e. account nonce). The request returned from this method will be used by the caller (in a different execution environment) to call the &#x60;/construction/metadata&#x60; endpoint.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConstructionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConstructionApi apiInstance = new ConstructionApi(defaultClient);
    ConstructionPreprocessRequest constructionPreprocessRequest = new ConstructionPreprocessRequest(); // ConstructionPreprocessRequest | 
    try {
      ConstructionPreprocessResponse result = apiInstance.constructionPreprocess(constructionPreprocessRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConstructionApi#constructionPreprocess");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **constructionPreprocessRequest** | [**ConstructionPreprocessRequest**](ConstructionPreprocessRequest.md)|  |

### Return type

[**ConstructionPreprocessResponse**](ConstructionPreprocessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Expected response to a valid request |  -  |
**0** | unexpected error |  -  |

<a name="constructionSubmit"></a>
# **constructionSubmit**
> ConstructionSubmitResponse constructionSubmit(constructionSubmitRequest)

Submit a Signed Transaction

Submit a pre-signed transaction to the node. This call should not block on the transaction being included in a block. Rather, it should return immediately with an indication of whether or not the transaction was included in the mempool. The transaction submission response should only return a 200 status if the submitted transaction could be included in the mempool. Otherwise, it should return an error.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConstructionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConstructionApi apiInstance = new ConstructionApi(defaultClient);
    ConstructionSubmitRequest constructionSubmitRequest = new ConstructionSubmitRequest(); // ConstructionSubmitRequest | 
    try {
      ConstructionSubmitResponse result = apiInstance.constructionSubmit(constructionSubmitRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConstructionApi#constructionSubmit");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **constructionSubmitRequest** | [**ConstructionSubmitRequest**](ConstructionSubmitRequest.md)|  |

### Return type

[**ConstructionSubmitResponse**](ConstructionSubmitResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Expected response to a valid request |  -  |
**0** | unexpected error |  -  |

