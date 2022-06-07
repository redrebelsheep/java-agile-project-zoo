# OpenApiDefinition.ZooAccountControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addEntry**](ZooAccountControllerApi.md#addEntry) | **POST** /zoo/account | 
[**deleteByAccount**](ZooAccountControllerApi.md#deleteByAccount) | **DELETE** /zoo/account/{id} | 
[**getAllEntries**](ZooAccountControllerApi.md#getAllEntries) | **GET** /zoo/account | 

<a name="addEntry"></a>
# **addEntry**
> ZooAccount addEntry(body)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.ZooAccountControllerApi();
let body = new OpenApiDefinition.ZooAccount(); // ZooAccount | 

apiInstance.addEntry(body, (error, data, response) => {
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
 **body** | [**ZooAccount**](ZooAccount.md)|  | 

### Return type

[**ZooAccount**](ZooAccount.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteByAccount"></a>
# **deleteByAccount**
> Object deleteByAccount(id)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.ZooAccountControllerApi();
let id = 789; // Number | 

apiInstance.deleteByAccount(id, (error, data, response) => {
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
 **id** | **Number**|  | 

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getAllEntries"></a>
# **getAllEntries**
> [ZooAccount] getAllEntries()



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.ZooAccountControllerApi();
apiInstance.getAllEntries((error, data, response) => {
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

[**[ZooAccount]**](ZooAccount.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

