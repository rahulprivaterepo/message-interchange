FROM openjdk:17
ADD build/libs/pubsub-2.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]