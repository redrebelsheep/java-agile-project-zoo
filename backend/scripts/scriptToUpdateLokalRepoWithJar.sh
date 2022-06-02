#!/bin/bash

#mkdir ordner
mkdir swagger-service

#copy
cp swagger-codegenfile/swagger-codegen-cli.jar swagger-service/

cd swagger-service

#projekt muss gestartet sein
# java -jar swagger-codegen-cli.jar generate -l java -i http://localhost:8080/v3/api-docs --group-id de.vw --artifact-id zooBackend-client --artifact-version 0.0.1-SNAPSHOT -DhideGenerationTimestamp=true
java -jar swagger-codegen-cli.jar generate -i http://localhost:8080/v3/api-docs -l javascript -o ./app/generated

mvn package

mvn install

cd ..

rm -rf swagger-service
