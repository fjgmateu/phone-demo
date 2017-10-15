#! /bin/bash -e
echo "Parameters entorno de :: " $profile

echo application $application
echo profile $profile

export SPRING_APPLICATION_JSON=$(curl "https://gitlab.asefa.es/microservices/config-repo/raw/master/$application-$profile.json")

echo $SPRING_APPLICATION_JSON

echo "Starting  service..."

java -jar -Duser.country="ES" -Duser.language="es" /opt/service/*.jar


