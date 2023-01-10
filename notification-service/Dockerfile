FROM openjdk:17-alpine

# label for the image
LABEL Description="Notification- Service" Version="1.0"

# mount the temp volume
VOLUME /tmp

# Add the service as app.jar
ADD target/notification-service-1.0-SNAPSHOT.jar notification-service-1.0-SNAPSHOT.jar
# port
EXPOSE 9090

# touch the archive for timestamp
#RUN sh -c 'touch /app.jar'

# entrypoint to the image on run
ENTRYPOINT ["java", "-jar", "notification-service-1.0-SNAPSHOT.jar"]