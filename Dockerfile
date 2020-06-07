FROM ubuntu:16.04

WORKDIR /usr/local/bin

EXPOSE 8082

RUN curl -X GET 192.168.160.99:8082/artifactory/libs-release/pt/ua/busfind/busfind/0.0.1/busfind-0.0.1.jar --output busfind-0.0.1.jar


CMD ["java","-jar", "busfind-0.0.1.jar"]
