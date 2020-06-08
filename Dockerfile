# FROM ubuntu:16.04

# RUN apt-get update && \
#     apt-get -y install sudo curl openjdk-8-jdk

# RUN adduser --disabled-password --gecos '' newuser \
#     && adduser newuser sudo \
#     && echo '%sudo ALL=(ALL:ALL) ALL' >> /etc/sudoers

# RUN mkdir -p /app
# RUN chown newuser /app
# USER newuser
# WORKDIR /app

# RUN curl -X GET 192.168.160.99:8082/artifactory/libs-release/pt/ua/busfind/busfind/0.0.1/busfind-0.0.1.jar --output busfind-0.0.1.jar

# RUN pwd
# RUN ls

# FROM openjdk:8-jre-alpine

# ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
# RUN export JAVA_HOME

# CMD ["java","-jar", "busfind-0.0.1.jar"]

FROM openjdk:8-jre-alpine 
WORKDIR /app
RUN apk --no-cache add curl
RUN curl -X GET 192.168.160.99:8082/artifactory/libs-release/pt/ua/busfind/busfind/0.0.1/busfind-0.0.1.jar --output busfind-0.0.1.jar
ENTRYPOINT ["java","-jar", "busfind-0.0.1.jar"]
