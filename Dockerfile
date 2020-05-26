FROM openjdk:8-jre-alpine 
RUN curl -X GET http://192.168.160.99:8082/artifactory/libs-release-local/pt/ua/bsufind/busfind/0.0.1/busfind-0.0.1.jar -O
ENTRYPOINT ["java","-jar", "/busfind-0.0.1.jar"]
