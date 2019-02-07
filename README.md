# URL Shortener API

Implementation of simple URL shortener API. 

For a given URL, the web app will produce a shortened version of it (based on given criteria). Once the user visits shortened URL, they will be redirected to original URL (301 Moved Permanently).

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Technologies

Here are the languages used, the tools and its versions.
* Java JDK 8
* Maven
* Spring Boot
* MySQL
* Docker

## Installing

In order to use api do git clone, pull dependencies and start database

### Or

simply use Docker https://forums.docker.com/t/best-practices-for-getting-code-into-a-container-git-clone-vs-copy-vs-data-container/4077
to build project image, build MySQL image and compose them. 

If you are using a custome port remember to add it manually to your shortener url when you use it. This example is for testing purpose. 

### Files
* /dockerfile - For building project docker image 
* /docker-compose.yml - To compose images. Here is this file: 

```
version: '3'

services:
  docker-mysql:
    image: mysql:5.7
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_DATABASE=url_shortener
      - MYSQL_PASSWORD=root
  spring-boot-jpa-docker-webapp:
    image: url-shortener-app
    depends_on:
      - docker-mysql
    ports:
      - 8181:8080
    environment:
      - DATABASE_HOST=docker-mysql
      - DATABASE_USER=root
      - DATABASE_PASSWORD=root
      - DATABASE_NAME=url_shortener
      - DATABASE_PORT=3306
```
## Test Examples

POST to /shorten to create a shortened URL:
$ curl -X POST -H 'Content-Type: application/json' -d '{"url": "https://www.google.com"}' http://localhost/shorten
It should respond with body like
{"original_link":"https://www.google.com", "short_link":"http://localhost/95UdH"}

Then, when issuing a GET request to a shortened URL:
$ curl http://localhost/95UdH -v
The response should be 301 Moved Permanently
< HTTP/1.1 301 Moved Permanently
< Content-Length: 0
< Location: https://www.google.com


Enjoy :)
