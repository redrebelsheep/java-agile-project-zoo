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
 * The ZooAccount model module.
 * @module model/ZooAccount
 * @version v0
 */
export class ZooAccount {
  /**
   * Constructs a new <code>ZooAccount</code>.
   * @alias module:model/ZooAccount
   * @class
   * @param _date {Date} 
   * @param bookingType {module:model/ZooAccount.BookingTypeEnum} 
   * @param valueOfBooking {Number} 
   * @param bankBalance {Number} 
   */
  constructor(_date, bookingType, valueOfBooking, bankBalance) {
    this._date = _date;
    this.bookingType = bookingType;
    this.valueOfBooking = valueOfBooking;
    this.bankBalance = bankBalance;
  }

  /**
   * Constructs a <code>ZooAccount</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/ZooAccount} obj Optional instance to populate.
   * @return {module:model/ZooAccount} The populated <code>ZooAccount</code> instance.
   */
  static constructFromObject(data, obj) {
    if (data) {
      obj = obj || new ZooAccount();
      if (data.hasOwnProperty('id'))
        obj.id = ApiClient.convertToType(data['id'], 'Number');
      if (data.hasOwnProperty('date'))
        obj._date = ApiClient.convertToType(data['date'], 'Date');
      if (data.hasOwnProperty('bookingType'))
        obj.bookingType = ApiClient.convertToType(data['bookingType'], 'String');
      if (data.hasOwnProperty('usageOfBooking'))
        obj.usageOfBooking = ApiClient.convertToType(data['usageOfBooking'], 'String');
      if (data.hasOwnProperty('valueOfBooking'))
        obj.valueOfBooking = ApiClient.convertToType(data['valueOfBooking'], 'Number');
      if (data.hasOwnProperty('bankBalance'))
        obj.bankBalance = ApiClient.convertToType(data['bankBalance'], 'Number');
    }
    return obj;
  }
}

/**
 * @member {Number} id
 */
ZooAccount.prototype.id = undefined;

/**
 * @member {Date} _date
 */
ZooAccount.prototype._date = undefined;

/**
 * Allowed values for the <code>bookingType</code> property.
 * @enum {String}
 * @readonly
 */
ZooAccount.BookingTypeEnum = {
  /**
   * value: "Einkommen"
   * @const
   */
  einkommen: "Einkommen",

  /**
   * value: "Ausgabe"
   * @const
   */
  ausgabe: "Ausgabe",

  /**
   * value: "Spende"
   * @const
   */
  spende: "Spende"
};
/**
 * @member {module:model/ZooAccount.BookingTypeEnum} bookingType
 */
ZooAccount.prototype.bookingType = undefined;

/**
 * @member {String} usageOfBooking
 */
ZooAccount.prototype.usageOfBooking = undefined;

/**
 * @member {Number} valueOfBooking
 */
ZooAccount.prototype.valueOfBooking = undefined;

/**
 * @member {Number} bankBalance
 */
ZooAccount.prototype.bankBalance = undefined;

