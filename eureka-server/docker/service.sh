#! /bin/bash -e
echo "Parameters entorno de :: " $profile

echo "Starting  service..."

java -jar -Duser.country="ES" -Duser.language="es" /opt/service/*.jar


