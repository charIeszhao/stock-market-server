FROM openjdk:8-jdk-alpine

ADD target/stockmarket-gateway-service-0.0.1-SNAPSHOT.jar /opt/app.jar

EXPOSE 5555

CMD ["/usr/bin/java","-jar","/opt/app.jar"]