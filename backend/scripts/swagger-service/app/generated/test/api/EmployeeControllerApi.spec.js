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
    instance = new OpenApiDefinition.EmployeeControllerApi();
  });

  describe('(package)', function() {
    describe('EmployeeControllerApi', function() {
      describe('addEmployee', function() {
        it('should call addEmployee successfully', function(done) {
          // TODO: uncomment, update parameter values for addEmployee call and complete the assertions
          /*

          instance.addEmployee(body, function(error, data, response) {
            if (error) {
              done(error);
              return;
            }
            // TODO: update response assertions
            expect(data).to.be.a(OpenApiDefinition.Employee);

            done();
          });
          */
          // TODO: uncomment and complete method invocation above, then delete this line and the next:
          done();
        });
      });
      describe('deleteEmployee', function() {
        it('should call deleteEmployee successfully', function(done) {
          // TODO: uncomment, update parameter values for deleteEmployee call and complete the assertions
          /*

          instance.deleteEmployee(id, function(error, data, response) {
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
      describe('getAllEmployee', function() {
        it('should call getAllEmployee successfully', function(done) {
          // TODO: uncomment getAllEmployee call and complete the assertions
          /*

          instance.getAllEmployee(function(error, data, response) {
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
              expect(data).to.be.a(OpenApiDefinition.EmployeeDTO);
            }

            done();
          });
          */
          // TODO: uncomment and complete method invocation above, then delete this line and the next:
          done();
        });
      });
      describe('getAllWithJobEmployee', function() {
        it('should call getAllWithJobEmployee successfully', function(done) {
          // TODO: uncomment, update parameter values for getAllWithJobEmployee call and complete the assertions
          /*

          instance.getAllWithJobEmployee(job, function(error, data, response) {
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
              expect(data).to.be.a(OpenApiDefinition.EmployeeDTO);
            }

            done();
          });
          */
          // TODO: uncomment and complete method invocation above, then delete this line and the next:
          done();
        });
      });
      describe('getEmployeeById', function() {
        it('should call getEmployeeById successfully', function(done) {
          // TODO: uncomment, update parameter values for getEmployeeById call and complete the assertions
          /*

          instance.getEmployeeById(id, function(error, data, response) {
            if (error) {
              done(error);
              return;
            }
            // TODO: update response assertions
            expect(data).to.be.a(OpenApiDefinition.EmployeeDTO);

            done();
          });
          */
          // TODO: uncomment and complete method invocation above, then delete this line and the next:
          done();
        });
      });
      describe('putEmployee', function() {
        it('should call putEmployee successfully', function(done) {
          // TODO: uncomment, update parameter values for putEmployee call and complete the assertions
          /*

          instance.putEmployee(body, function(error, data, response) {
            if (error) {
              done(error);
              return;
            }
            // TODO: update response assertions
            expect(data).to.be.a(OpenApiDefinition.Employee);

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