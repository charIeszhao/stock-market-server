FROM openjdk:8-jdk-alpine

ADD target/stockmarket-eureka-server-0.0.1-SNAPSHOT.jar /opt/app.jar

EXPOSE 8761

CMD ["/usr/bin/java","-jar","/opt/app.jar"]