FROM openjdk:17-alpine

# label for the image
LABEL Description="Driver Service" Version="1.0"

# mount the temp volume
VOLUME /tmp

# Add the service as app.jar
ADD target/driver-service-1.0-SNAPSHOT.jar driver-service-1.0-SNAPSHOT.jar
# port
EXPOSE 8081

# entrypoint to the image on run
ENTRYPOINT ["java", "-jar", "driver-service-1.0-SNAPSHOT.jar"]