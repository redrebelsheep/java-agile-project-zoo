# OpenApiDefinition.StallControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addStall**](StallControllerApi.md#addStall) | **POST** /zoo/stall | 
[**deleteStall**](StallControllerApi.md#deleteStall) | **DELETE** /zoo/stall/{id} | 
[**getAllStalls**](StallControllerApi.md#getAllStalls) | **GET** /zoo/stalls | 
[**getStallById**](StallControllerApi.md#getStallById) | **GET** /zoo/stall/{id} | 
[**putStall**](StallControllerApi.md#putStall) | **PUT** /zoo/stall | 

<a name="addStall"></a>
# **addStall**
> Stall addStall(body)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.StallControllerApi();
let body = new OpenApiDefinition.StallDTO(); // StallDTO | 

apiInstance.addStall(body, (error, data, response) => {
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
 **body** | [**StallDTO**](StallDTO.md)|  | 

### Return type

[**Stall**](Stall.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteStall"></a>
# **deleteStall**
> &#x27;Number&#x27; deleteStall(id)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.StallControllerApi();
let id = 789; // Number | 

apiInstance.deleteStall(id, (error, data, response) => {
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

**&#x27;Number&#x27;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getAllStalls"></a>
# **getAllStalls**
> [StallDTO] getAllStalls()



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.StallControllerApi();
apiInstance.getAllStalls((error, data, response) => {
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

[**[StallDTO]**](StallDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getStallById"></a>
# **getStallById**
> StallDTO getStallById(id)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.StallControllerApi();
let id = 789; // Number | 

apiInstance.getStallById(id, (error, data, response) => {
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

[**StallDTO**](StallDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="putStall"></a>
# **putStall**
> Stall putStall(body)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.StallControllerApi();
let body = new OpenApiDefinition.StallDTO(); // StallDTO | 

apiInstance.putStall(body, (error, data, response) => {
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
 **body** | [**StallDTO**](StallDTO.md)|  | 

### Return type

[**Stall**](Stall.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

