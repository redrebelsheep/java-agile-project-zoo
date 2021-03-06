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

  beforeEach(function() {
    instance = new OpenApiDefinition.EnclosureControllerApi();
  });

  describe('(package)', function() {
    describe('EnclosureControllerApi', function() {
      describe('addEnclosure', function() {
        it('should call addEnclosure successfully', function(done) {
          // TODO: uncomment, update parameter values for addEnclosure call and complete the assertions
          /*

          instance.addEnclosure(body, function(error, data, response) {
            if (error) {
              done(error);
              return;
            }
            // TODO: update response assertions
            expect(data).to.be.a(OpenApiDefinition.Enclosure);

            done();
          });
          */
          // TODO: uncomment and complete method invocation above, then delete this line and the next:
          done();
        });
      });
      describe('deleteEnclosure', function() {
        it('should call deleteEnclosure successfully', function(done) {
          // TODO: uncomment, update parameter values for deleteEnclosure call and complete the assertions
          /*

          instance.deleteEnclosure(id, function(error, data, response) {
            if (error) {
              done(error);
              return;
            }
            // TODO: update response assertions
            expect(data).to.be.a(&#x27;number&#x27;);
            // expect(data).to.be(null);

            done();
          });
          */
          // TODO: uncomment and complete method invocation above, then delete this line and the next:
          done();
        });
      });
      describe('getAllEnclosures', function() {
        it('should call getAllEnclosures successfully', function(done) {
          // TODO: uncomment getAllEnclosures call and complete the assertions
          /*

          instance.getAllEnclosures(function(error, data, response) {
            if (error) {
              done(error);
              return;
            }
            // TODO: update response assertions
            let dataCtr = data;
            expect(dataCtr).to.be.an(Array);
            expect(dataCtr).to.not.be.empty();
            for (let p in dataCtr) {
              let data = dataCtr[p];
              expect(data).to.be.a(OpenApiDefinition.EnclosureDTO);
            }

            done();
          });
          */
          // TODO: uncomment and complete method invocation above, then delete this line and the next:
          done();
        });
      });
      describe('getEnclosureById', function() {
        it('should call getEnclosureById successfully', function(done) {
          // TODO: uncomment, update parameter values for getEnclosureById call and complete the assertions
          /*

          instance.getEnclosureById(id, function(error, data, response) {
            if (error) {
              done(error);
              return;
            }
            // TODO: update response assertions
            expect(data).to.be.a(OpenApiDefinition.EnclosureDTO);

            done();
          });
          */
          // TODO: uncomment and complete method invocation above, then delete this line and the next:
          done();
        });
      });
      describe('putEnclosure', function() {
        it('should call putEnclosure successfully', function(done) {
          // TODO: uncomment, update parameter values for putEnclosure call and complete the assertions
          /*

          instance.putEnclosure(body, function(error, data, response) {
            if (error) {
              done(error);
              return;
            }
            // TODO: update response assertions
            expect(data).to.be.a(OpenApiDefinition.Enclosure);

            done();
          });
          */
          // TODO: uncomment and complete method invocation above, then delete this line and the next:
          done();
        });
      });
    });
  });

}));
