# Spring Boot and Docker

## Setup _username/.m2/settings.xml_ for pushing images to the [DockerHub](https://hub.docker.com/):

```xml
<servers>
  <server>
    <id>docker.io</id>
    <username>username</username>
    <password>password</password>
  </server>
</servers>
```

# Maven Fabric8 commands

## Run docker image:

``
mvn docker:run
``

## Stop docker image:

``
mvn docker:stop
``

## Start docker image in background:

``
mvn docker:start
``

## Build and push image to the local docker repo:

``
mvn clean package docker:build
``

## Build and push image to the [DockerHub](https://hub.docker.com/):

``
mvn clean package docker:build docker:push
``

## Example MVN Command for CI:

``
mvn clean package verify docker:push
``