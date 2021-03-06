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
import {ApiClient} from '../ApiClient';

/**
 * The EnclosureDTO model module.
 * @module model/EnclosureDTO
 * @version v0
 */
export class EnclosureDTO {
  /**
   * Constructs a new <code>EnclosureDTO</code>.
   * @alias module:model/EnclosureDTO
   * @class
   * @param name {String} 
   * @param maintenanceCosts {Number} 
   */
  constructor(name, maintenanceCosts) {
    this.name = name;
    this.maintenanceCosts = maintenanceCosts;
  }

  /**
   * Constructs a <code>EnclosureDTO</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/EnclosureDTO} obj Optional instance to populate.
   * @return {module:model/EnclosureDTO} The populated <code>EnclosureDTO</code> instance.
   */
  static constructFromObject(data, obj) {
    if (data) {
      obj = obj || new EnclosureDTO();
      if (data.hasOwnProperty('id'))
        obj.id = ApiClient.convertToType(data['id'], 'Number');
      if (data.hasOwnProperty('name'))
        obj.name = ApiClient.convertToType(data['name'], 'String');
      if (data.hasOwnProperty('maintenanceCosts'))
        obj.maintenanceCosts = ApiClient.convertToType(data['maintenanceCosts'], 'Number');
      if (data.hasOwnProperty('staff'))
        obj.staff = ApiClient.convertToType(data['staff'], ['Number']);
      if (data.hasOwnProperty('animals'))
        obj.animals = ApiClient.convertToType(data['animals'], ['Number']);
    }
    return obj;
  }
}

/**
 * @member {Number} id
 */
EnclosureDTO.prototype.id = undefined;

/**
 * @member {String} name
 */
EnclosureDTO.prototype.name = undefined;

/**
 * @member {Number} maintenanceCosts
 */
EnclosureDTO.prototype.maintenanceCosts = undefined;

/**
 * @member {Array.<Number>} staff
 */
EnclosureDTO.prototype.staff = undefined;

/**
 * @member {Array.<Number>} animals
 */
EnclosureDTO.prototype.animals = undefined;

