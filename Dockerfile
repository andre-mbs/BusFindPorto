FROM openjdk:8-jre-alpine 
WORKDIR /app
RUN apk --no-cache add curl
RUN curl -X GET 192.168.160.99:8082/artifactory/libs-release/pt/ua/busfind/busfind/0.0.1/busfind-0.0.1.jar --output busfind-0.0.1.jar
ENTRYPOINT ["java","-jar", "busfind-0.0.1.jar"]
