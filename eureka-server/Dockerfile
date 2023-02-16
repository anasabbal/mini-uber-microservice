FROM openjdk:17-alpine

# label for the image
LABEL Description="Eureka Server" Version="1.0"

RUN ./mvn
# mount the temp volume
VOLUME /tmp

# Add the service as app.jar
ADD target/eureka-server-1.0-SNAPSHOT.jar eureka-server-1.0-SNAPSHOT.jar
# port
EXPOSE 8761

# touch the archive for timestamp
#RUN sh -c 'touch /app.jar'

# entrypoint to the image on run
ENTRYPOINT ["java", "-jar", "eureka-server-1.0-SNAPSHOT.jar"]