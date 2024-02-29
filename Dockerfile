FROM openjdk:17
ADD build/libs/message-interchange-v1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]