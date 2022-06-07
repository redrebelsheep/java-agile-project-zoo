# OpenApiDefinition.EmployeeControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addEmployee**](EmployeeControllerApi.md#addEmployee) | **POST** /zoo/employee | 
[**deleteEmployee**](EmployeeControllerApi.md#deleteEmployee) | **DELETE** /zoo/employee/{id} | 
[**getAllEmployee**](EmployeeControllerApi.md#getAllEmployee) | **GET** /zoo/employees | 
[**getAllWithJobEmployee**](EmployeeControllerApi.md#getAllWithJobEmployee) | **GET** /zoo/employees/{job} | 
[**getEmployeeById**](EmployeeControllerApi.md#getEmployeeById) | **GET** /zoo/employee/{id} | 
[**putEmployee**](EmployeeControllerApi.md#putEmployee) | **PUT** /zoo/employee | 

<a name="addEmployee"></a>
# **addEmployee**
> Employee addEmployee(body)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.EmployeeControllerApi();
let body = new OpenApiDefinition.EmployeeDTO(); // EmployeeDTO | 

apiInstance.addEmployee(body, (error, data, response) => {
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
 **body** | [**EmployeeDTO**](EmployeeDTO.md)|  | 

### Return type

[**Employee**](Employee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteEmployee"></a>
# **deleteEmployee**
> &#x27;Number&#x27; deleteEmployee(id)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.EmployeeControllerApi();
let id = 789; // Number | 

apiInstance.deleteEmployee(id, (error, data, response) => {
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

<a name="getAllEmployee"></a>
# **getAllEmployee**
> [EmployeeDTO] getAllEmployee()



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.EmployeeControllerApi();
apiInstance.getAllEmployee((error, data, response) => {
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

[**[EmployeeDTO]**](EmployeeDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getAllWithJobEmployee"></a>
# **getAllWithJobEmployee**
> [EmployeeDTO] getAllWithJobEmployee(job)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.EmployeeControllerApi();
let job = "job_example"; // String | 

apiInstance.getAllWithJobEmployee(job, (error, data, response) => {
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
 **job** | **String**|  | 

### Return type

[**[EmployeeDTO]**](EmployeeDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getEmployeeById"></a>
# **getEmployeeById**
> EmployeeDTO getEmployeeById(id)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.EmployeeControllerApi();
let id = 789; // Number | 

apiInstance.getEmployeeById(id, (error, data, response) => {
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

[**EmployeeDTO**](EmployeeDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="putEmployee"></a>
# **putEmployee**
> Employee putEmployee(body)



### Example
```javascript
import {OpenApiDefinition} from 'open_api_definition';

let apiInstance = new OpenApiDefinition.EmployeeControllerApi();
let body = new OpenApiDefinition.EmployeeDTO(); // EmployeeDTO | 

apiInstance.putEmployee(body, (error, data, response) => {
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
 **body** | [**EmployeeDTO**](EmployeeDTO.md)|  | 

### Return type

[**Employee**](Employee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

