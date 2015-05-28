FROM java:8u45
MAINTAINER Gregory Le Bonniec "gregory.le.bonniec@ellixo.com"

ADD springboot-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-Dspring.profiles.active=test", "-jar", "/app.jar" ]