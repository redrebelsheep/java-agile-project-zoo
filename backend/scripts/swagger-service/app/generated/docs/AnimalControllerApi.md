# OpenApiDefinition.AnimalControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addAnimal**](AnimalControllerApi.md#addAnimal) | **POST** /zoo/animal | 
[**deleteAnimal**](AnimalControllerApi.md#deleteAnimal) | **DELETE** /zoo/animal/{id} | 
[**getAllAnimals**](AnimalControllerApi.md#getAllAnimals) | **GET** /zoo/animals | 
[**getAnimalById**](AnimalControllerApi.md#getAnimalById) | **GET** /zoo/animal/{id} | 
[**putAnimal**](AnimalControllerApi.md#putAnimal) | **PUT** /zoo/animal | 

<a name="addAnimal"></a>
# **addAnimal**
> Animal addAnimal(body)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.AnimalControllerApi();
let body = new OpenApiDefinition.AnimalDTO(); // AnimalDTO | 

apiInstance.addAnimal(body, (error, data, response) => {
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
 **body** | [**AnimalDTO**](AnimalDTO.md)|  | 

### Return type

[**Animal**](Animal.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteAnimal"></a>
# **deleteAnimal**
> &#x27;Number&#x27; deleteAnimal(id)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.AnimalControllerApi();
let id = 789; // Number | 

apiInstance.deleteAnimal(id, (error, data, response) => {
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

<a name="getAllAnimals"></a>
# **getAllAnimals**
> [AnimalDTO] getAllAnimals()



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.AnimalControllerApi();
apiInstance.getAllAnimals((error, data, response) => {
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

[**[AnimalDTO]**](AnimalDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getAnimalById"></a>
# **getAnimalById**
> AnimalDTO getAnimalById(id)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.AnimalControllerApi();
let id = 789; // Number | 

apiInstance.getAnimalById(id, (error, data, response) => {
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

[**AnimalDTO**](AnimalDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="putAnimal"></a>
# **putAnimal**
> Animal putAnimal(body)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.AnimalControllerApi();
let body = new OpenApiDefinition.AnimalDTO(); // AnimalDTO | 

apiInstance.putAnimal(body, (error, data, response) => {
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
 **body** | [**AnimalDTO**](AnimalDTO.md)|  | 

### Return type

[**Animal**](Animal.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

