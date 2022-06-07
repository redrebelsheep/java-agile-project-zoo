# OpenApiDefinition.ZooHistoryControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addZooHistory**](ZooHistoryControllerApi.md#addZooHistory) | **POST** /zoo/history | 
[**deleteAllHistory**](ZooHistoryControllerApi.md#deleteAllHistory) | **DELETE** /zoo/history | 
[**getFullHistory**](ZooHistoryControllerApi.md#getFullHistory) | **GET** /zoo/history | 

<a name="addZooHistory"></a>
# **addZooHistory**
> ZooHistory addZooHistory(body)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.ZooHistoryControllerApi();
let body = new OpenApiDefinition.ZooHistory(); // ZooHistory | 

apiInstance.addZooHistory(body, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ZooHistory**](ZooHistory.md)|  | 

### Return type

[**ZooHistory**](ZooHistory.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteAllHistory"></a>
# **deleteAllHistory**
> Object deleteAllHistory()



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.ZooHistoryControllerApi();
apiInstance.deleteAllHistory((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getFullHistory"></a>
# **getFullHistory**
> [ZooHistory] getFullHistory()



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.ZooHistoryControllerApi();
apiInstance.getFullHistory((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**[ZooHistory]**](ZooHistory.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

