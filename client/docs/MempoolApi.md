# MempoolApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**mempool**](MempoolApi.md#mempool) | **POST** /mempool | Get All Mempool Transactions
[**mempoolTransaction**](MempoolApi.md#mempoolTransaction) | **POST** /mempool/transaction | Get a Mempool Transaction


<a name="mempool"></a>
# **mempool**
> MempoolResponse mempool(networkRequest)

Get All Mempool Transactions

Get all Transaction Identifiers in the mempool

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MempoolApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    MempoolApi apiInstance = new MempoolApi(defaultClient);
    NetworkRequest networkRequest = new NetworkRequest(); // NetworkRequest | 
    try {
      MempoolResponse result = apiInstance.mempool(networkRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MempoolApi#mempool");
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
 **networkRequest** | [**NetworkRequest**](NetworkRequest.md)|  |

### Return type

[**MempoolResponse**](MempoolResponse.md)

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

<a name="mempoolTransaction"></a>
# **mempoolTransaction**
> MempoolTransactionResponse mempoolTransaction(mempoolTransactionRequest)

Get a Mempool Transaction

Get a transaction in the mempool by its Transaction Identifier. This is a separate request than fetching a block transaction (/block/transaction) because some blockchain nodes need to know that a transaction query is for something in the mempool instead of a transaction in a block. Transactions may not be fully parsable until they are in a block (ex: may not be possible to determine the fee to pay before a transaction is executed). On this endpoint, it is ok that returned transactions are only estimates of what may actually be included in a block.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MempoolApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    MempoolApi apiInstance = new MempoolApi(defaultClient);
    MempoolTransactionRequest mempoolTransactionRequest = new MempoolTransactionRequest(); // MempoolTransactionRequest | 
    try {
      MempoolTransactionResponse result = apiInstance.mempoolTransaction(mempoolTransactionRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MempoolApi#mempoolTransaction");
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
 **mempoolTransactionRequest** | [**MempoolTransactionRequest**](MempoolTransactionRequest.md)|  |

### Return type

[**MempoolTransactionResponse**](MempoolTransactionResponse.md)

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

