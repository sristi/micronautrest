FROM openjdk:14-alpine
COPY target/micronautrest-*.jar micronautrest.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "micronautrest.jar"]