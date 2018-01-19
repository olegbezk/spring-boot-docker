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

# Docker Compose

## Run docker compose script in directory with *docker-compose.yml* file:

``
docker-compose up -d
``

## Stop docker compose script in directory with *docker-compose.yml* file:

``
docker-compose down
``

# Docker swarm mode

## Get info about docker:

``
docker info
``

## Init docker in swarm mode:

``
docker swarm init
`` 

## Manage a swarm cluster with *Portainer*:

```sh
docker service create \
--name portainer \
--publish 9000:9000 \
--replicas=1 \
--constraint 'node.role == manager' \
--mount type=bind,src=//var/run/docker.sock,dst=/var/run/docker.sock \
portainer/portainer \
-H unix:///var/run/docker.sock
```
# Specify key for access to the [DigitalOcean](https://www.digitalocean.com/) server:

``
ssh -i "~/.ssh/<key-name>.pub" root@<node-ip>
``