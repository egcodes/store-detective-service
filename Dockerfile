FROM adoptopenjdk/openjdk11:ubi
MAINTAINER egcodes
COPY target/store-detective-service-0.1.0.jar store-detective-service-0.1.0.jar
ENTRYPOINT ["java","-jar","/store-detective-service-0.1.0.jar"]
