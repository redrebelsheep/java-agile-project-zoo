# OpenApiDefinition.EnclosureControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addEnclosure**](EnclosureControllerApi.md#addEnclosure) | **POST** /zoo/enclosure | 
[**deleteEnclosure**](EnclosureControllerApi.md#deleteEnclosure) | **DELETE** /zoo/enclosure/{id} | 
[**getAllEnclosures**](EnclosureControllerApi.md#getAllEnclosures) | **GET** /zoo/enclosures | 
[**getEnclosureById**](EnclosureControllerApi.md#getEnclosureById) | **GET** /zoo/enclosure/{id} | 
[**putEnclosure**](EnclosureControllerApi.md#putEnclosure) | **PUT** /zoo/enclosure | 

<a name="addEnclosure"></a>
# **addEnclosure**
> Enclosure addEnclosure(body)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.EnclosureControllerApi();
let body = new OpenApiDefinition.EnclosureDTO(); // EnclosureDTO | 

apiInstance.addEnclosure(body, (error, data, response) => {
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
 **body** | [**EnclosureDTO**](EnclosureDTO.md)|  | 

### Return type

[**Enclosure**](Enclosure.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteEnclosure"></a>
# **deleteEnclosure**
> &#x27;Number&#x27; deleteEnclosure(id)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.EnclosureControllerApi();
let id = 789; // Number | 

apiInstance.deleteEnclosure(id, (error, data, response) => {
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

<a name="getAllEnclosures"></a>
# **getAllEnclosures**
> [EnclosureDTO] getAllEnclosures()



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.EnclosureControllerApi();
apiInstance.getAllEnclosures((error, data, response) => {
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

[**[EnclosureDTO]**](EnclosureDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getEnclosureById"></a>
# **getEnclosureById**
> EnclosureDTO getEnclosureById(id)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.EnclosureControllerApi();
let id = 789; // Number | 

apiInstance.getEnclosureById(id, (error, data, response) => {
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

[**EnclosureDTO**](EnclosureDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="putEnclosure"></a>
# **putEnclosure**
> Enclosure putEnclosure(body)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.EnclosureControllerApi();
let body = new OpenApiDefinition.EnclosureDTO(); // EnclosureDTO | 

apiInstance.putEnclosure(body, (error, data, response) => {
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
 **body** | [**EnclosureDTO**](EnclosureDTO.md)|  | 

### Return type

[**Enclosure**](Enclosure.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

