/*
 * OpenAPI definition
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v0
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 *
 * Swagger Codegen version: 3.0.34
 *
 * Do not edit the class manually.
 *
 */
import {ApiClient} from './ApiClient';
import {Animal} from './model/Animal';
import {AnimalDTO} from './model/AnimalDTO';
import {Employee} from './model/Employee';
import {EmployeeDTO} from './model/EmployeeDTO';
import {Enclosure} from './model/Enclosure';
import {EnclosureDTO} from './model/EnclosureDTO';
import {Stall} from './model/Stall';
import {StallDTO} from './model/StallDTO';
import {ZooAccount} from './model/ZooAccount';
import {ZooHistory} from './model/ZooHistory';
import {AnimalControllerApi} from './api/AnimalControllerApi';
import {EmployeeControllerApi} from './api/EmployeeControllerApi';
import {EnclosureControllerApi} from './api/EnclosureControllerApi';
import {StallControllerApi} from './api/StallControllerApi';
import {ZooAccountControllerApi} from './api/ZooAccountControllerApi';
import {ZooHistoryControllerApi} from './api/ZooHistoryControllerApi';

/**
* Object.<br>
* The <code>index</code> module provides access to constructors for all the classes which comprise the public API.
* <p>
* An AMD (recommended!) or CommonJS application will generally do something equivalent to the following:
* <pre>
* var OpenApiDefinition = require('index'); // See note below*.
* var xxxSvc = new OpenApiDefinition.XxxApi(); // Allocate the API class we're going to use.
* var yyyModel = new OpenApiDefinition.Yyy(); // Construct a model instance.
* yyyModel.someProperty = 'someValue';
* ...
* var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
* ...
* </pre>
* <em>*NOTE: For a top-level AMD script, use require(['index'], function(){...})
* and put the application logic within the callback function.</em>
* </p>
* <p>
* A non-AMD browser application (discouraged) might do something like this:
* <pre>
* var xxxSvc = new OpenApiDefinition.XxxApi(); // Allocate the API class we're going to use.
* var yyy = new OpenApiDefinition.Yyy(); // Construct a model instance.
* yyyModel.someProperty = 'someValue';
* ...
* var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
* ...
* </pre>
* </p>
* @module index
* @version v0
*/
export {
    /**
     * The ApiClient constructor.
     * @property {module:ApiClient}
     */
    ApiClient,

    /**
     * The Animal model constructor.
     * @property {module:model/Animal}
     */
    Animal,

    /**
     * The AnimalDTO model constructor.
     * @property {module:model/AnimalDTO}
     */
    AnimalDTO,

    /**
     * The Employee model constructor.
     * @property {module:model/Employee}
     */
    Employee,

    /**
     * The EmployeeDTO model constructor.
     * @property {module:model/EmployeeDTO}
     */
    EmployeeDTO,

    /**
     * The Enclosure model constructor.
     * @property {module:model/Enclosure}
     */
    Enclosure,

    /**
     * The EnclosureDTO model constructor.
     * @property {module:model/EnclosureDTO}
     */
    EnclosureDTO,

    /**
     * The Stall model constructor.
     * @property {module:model/Stall}
     */
    Stall,

    /**
     * The StallDTO model constructor.
     * @property {module:model/StallDTO}
     */
    StallDTO,

    /**
     * The ZooAccount model constructor.
     * @property {module:model/ZooAccount}
     */
    ZooAccount,

    /**
     * The ZooHistory model constructor.
     * @property {module:model/ZooHistory}
     */
    ZooHistory,

    /**
    * The AnimalControllerApi service constructor.
    * @property {module:api/AnimalControllerApi}
    */
    AnimalControllerApi,

    /**
    * The EmployeeControllerApi service constructor.
    * @property {module:api/EmployeeControllerApi}
    */
    EmployeeControllerApi,

    /**
    * The EnclosureControllerApi service constructor.
    * @property {module:api/EnclosureControllerApi}
    */
    EnclosureControllerApi,

    /**
    * The StallControllerApi service constructor.
    * @property {module:api/StallControllerApi}
    */
    StallControllerApi,

    /**
    * The ZooAccountControllerApi service constructor.
    * @property {module:api/ZooAccountControllerApi}
    */
    ZooAccountControllerApi,

    /**
    * The ZooHistoryControllerApi service constructor.
    * @property {module:api/ZooHistoryControllerApi}
    */
    ZooHistoryControllerApi
};
