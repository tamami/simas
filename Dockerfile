FROM eclipse-temurin:17.0.3_7-jre-focal

WORKDIR /app
COPY build/libs/*.jar /app/app.jar

#ENTRYPOINT [ "java", "-noverify", "-Dserver.port=5004", "-jar", "./app.jar" ]
ENTRYPOINT [ "java", "-jar", "./app.jar" ]