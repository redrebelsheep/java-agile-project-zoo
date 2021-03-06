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
(function(root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD.
    define(['expect.js', '../../src/index'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    factory(require('expect.js'), require('../../src/index'));
  } else {
    // Browser globals (root is window)
    factory(root.expect, root.OpenApiDefinition);
  }
}(this, function(expect, OpenApiDefinition) {
  'use strict';

  var instance;

  describe('(package)', function() {
    describe('EnclosureDTO', function() {
      beforeEach(function() {
        instance = new OpenApiDefinition.EnclosureDTO();
      });

      it('should create an instance of EnclosureDTO', function() {
        // TODO: update the code to test EnclosureDTO
        expect(instance).to.be.a(OpenApiDefinition.EnclosureDTO);
      });

      it('should have the property id (base name: "id")', function() {
        // TODO: update the code to test the property id
        expect(instance).to.have.property('id');
        // expect(instance.id).to.be(expectedValueLiteral);
      });

      it('should have the property name (base name: "name")', function() {
        // TODO: update the code to test the property name
        expect(instance).to.have.property('name');
        // expect(instance.name).to.be(expectedValueLiteral);
      });

      it('should have the property maintenanceCosts (base name: "maintenanceCosts")', function() {
        // TODO: update the code to test the property maintenanceCosts
        expect(instance).to.have.property('maintenanceCosts');
        // expect(instance.maintenanceCosts).to.be(expectedValueLiteral);
      });

      it('should have the property staff (base name: "staff")', function() {
        // TODO: update the code to test the property staff
        expect(instance).to.have.property('staff');
        // expect(instance.staff).to.be(expectedValueLiteral);
      });

      it('should have the property animals (base name: "animals")', function() {
        // TODO: update the code to test the property animals
        expect(instance).to.have.property('animals');
        // expect(instance.animals).to.be(expectedValueLiteral);
      });

    });
  });

}));
