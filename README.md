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
## Specify key for access to the [DigitalOcean](https://www.digitalocean.com/) server:

``
ssh -i "~/.ssh/<key-name>.pub" root@<node-ip>
``

## Create portainer service in docker swarm on port 80:

```sh
docker service create \
--name portainer \
--publish 80:9000 \
--constraint 'node.role == manager' \
--mount type=bind,src=/var/run/docker.sock,dst=/var/run/docker.sock \
portainer/portainer \
-H unix:///var/run/docker.sock
```

## Create Overlay Network:

``
docker network create --driver overlay quetzalko-service-network
``

## List docker networks:

``
docker network ls
``

## Force new quorum:

``
docker swarm init --force-new-cluster --advertise-addr node3:2377
``

## MySQL Service:

```sh
docker service create \
 --name mysqldb -p 3306:3306 \
 -e MYSQL_DATABASE=pageviewservice \
 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes \
 --network quetzalko-service-network \
mysql
```

## List Processes in service:

``
docker service ps <servicename>
``

## Rabbit MQ Service:

```sh
docker service create \
 --name rabbitmq -p 5671:5671 -p 5672:5672 \
 --network quetzalko-service-network \
rabbitmq
```

## Page view Service:

```sh
docker service create --name pageviewservice -p 8081:8081 \
 -e SPRING_DATASOURCE_URL=jdbc:mysql://mysqldb:3306/pageviewservice \
 -e SPRING_PROFILES_ACTIVE=mysql  \
 -e SPRING_RABBITMQ_HOST=rabbitmq \
 --network quetzalko-service-network \
springframeworkguru/pageviewservice
```

## Spring Boot Web App:

```sh
docker service create --name springbootwebapp -p 8080:8080 \
 -e SPRING_RABBITMQ_HOST=rabbitmq \
 --network quetzalko-service-network \
quetzalko/springbootdocker
```

## Docker logs for service:

``
docker service logs -f <service_name>
``

## Docker stack deploy services:

### Create file *docker-compose.yml*

``
docker stack deploy -c docker-compose.yml quetzalko-stack
``