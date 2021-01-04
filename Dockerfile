FROM ubuntu:latest

MAINTAINER Damon Vessey "dvessey2008@gmail.com"

RUN apt-get update && apt-get install -y openjdk-14-jdk

ENV dbuser=dvess
ENV dbpass=BlackFlag90!
ENV jdbcurl=jdbc:postgresql://prodscheddb.cuwlvffd8dag.us-east-2.rds.amazonaws.com:5432/prodscheddb

WORKDIR /usr/local/bin/production-scheduler

ADD . ./src/main/resources/static

ADD target/prodschedapp.jar .

ENTRYPOINT ["java", "-jar", "prodschedapp.jar"]