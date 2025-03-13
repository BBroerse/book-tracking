#!/bin/bash

echo "Building the backend image using gradlew..."
backend/gradlew -p backend bootBuildImage

if [ $? -ne 0 ]; then
  echo "Gradle build failed. Exiting..."
  exit 1
fi

echo "Starting the services using docker-compose..."
docker-compose up --force-recreate