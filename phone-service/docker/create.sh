#! /bin/bash -e  

imageId="docker-service"
versionId="v1.0"

cd ..

project_api=$(find -H . -name "*-api" | sed -e 's/^\.\///g')

echo ":::: Compile and build with gradle::::"
cd $project_api
gradle --refresh-dependencies clean build

echo ":::: Copy Artifactory ::::"
cd ../docker
mkdir -p service
cp ../$project_api/build/libs/*.jar service

echo ":::: Create image  $imageId::::"
docker build -t asefa/service/$imageId .

echo ":::: Create tag::::"
docker tag asefa/service/$imageId srvregdocker.asefa.es:5000/asefa/service/$imageId:$versionId

echo ":::: push image ::::"
docker push srvregdocker.asefa.es:5000/asefa/service/$imageId:$versionId