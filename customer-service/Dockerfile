FROM openjdk:17-alpine

# label for the image
LABEL Description="Customer service" Version="1.0"

# mount the temp volume
VOLUME /tmp

RUN mvn clean install -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target/

# Add the service as app.jar
ADD target/customer-service-1.0-SNAPSHOT.jar customer-service-1.0-SNAPSHOT.jar
# port
EXPOSE 8080

# entrypoint to the image on run
ENTRYPOINT ["java", "-jar", "customer-service-1.0-SNAPSHOT.jar"]