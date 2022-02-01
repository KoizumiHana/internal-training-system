#!/bin/bash

function unpack() {
  NAME=$1
  VERSION=$2

  cd build/libs
  echo "${NAME}"-"${VERSION}".jar
  java -jar -Djarmode=layertools "${NAME}"-"${VERSION}".jar extract
}

function build() {
  NAME=$1

  DOCKER_BUILDKIT=1 docker build -f ../../../build-scripts/docker/Dockerfile \
    --build-arg JAR_FOLDER=. \
    -t "${NAME}":latest \
    -t "${NAME}":layered .
}

APP_VERSION=0.0.1-SNAPSHOT

# Building the app
cd ../../file-storage

echo "Building JAR file"
gradle clean build -x test

echo "Unpacking JARs"
unpack file-storage ${APP_VERSION}

echo "Building Docker image"
build its/file-storage
