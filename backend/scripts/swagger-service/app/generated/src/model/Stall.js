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
 * The Stall model module.
 * @module model/Stall
 * @version v0
 */
export class Stall {
  /**
   * Constructs a new <code>Stall</code>.
   * @alias module:model/Stall
   * @class
   * @param operatingCost {Number} 
   * @param type {module:model/Stall.TypeEnum} 
   */
  constructor(operatingCost, type) {
    this.operatingCost = operatingCost;
    this.type = type;
  }

  /**
   * Constructs a <code>Stall</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/Stall} obj Optional instance to populate.
   * @return {module:model/Stall} The populated <code>Stall</code> instance.
   */
  static constructFromObject(data, obj) {
    if (data) {
      obj = obj || new Stall();
      if (data.hasOwnProperty('id'))
        obj.id = ApiClient.convertToType(data['id'], 'Number');
      if (data.hasOwnProperty('seller'))
        obj.seller = ApiClient.convertToType(data['seller'], 'Number');
      if (data.hasOwnProperty('operatingCost'))
        obj.operatingCost = ApiClient.convertToType(data['operatingCost'], 'Number');
      if (data.hasOwnProperty('type'))
        obj.type = ApiClient.convertToType(data['type'], 'String');
    }
    return obj;
  }
}

/**
 * @member {Number} id
 */
Stall.prototype.id = undefined;

/**
 * @member {Number} seller
 */
Stall.prototype.seller = undefined;

/**
 * @member {Number} operatingCost
 */
Stall.prototype.operatingCost = undefined;

/**
 * Allowed values for the <code>type</code> property.
 * @enum {String}
 * @readonly
 */
Stall.TypeEnum = {
  /**
   * value: "Nahrung"
   * @const
   */
  nahrung: "Nahrung",

  /**
   * value: "Getraenke"
   * @const
   */
  getraenke: "Getraenke",

  /**
   * value: "Souvenir"
   * @const
   */
  souvenir: "Souvenir"
};
/**
 * @member {module:model/Stall.TypeEnum} type
 */
Stall.prototype.type = undefined;

