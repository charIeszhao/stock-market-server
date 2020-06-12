FROM openjdk:8-jdk-alpine

ADD target/stockmarket-user-service-0.0.1-SNAPSHOT.jar /opt/app.jar

EXPOSE 8081

CMD ["/usr/bin/java","-jar","/opt/app.jar"]