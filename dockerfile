FROM openjdk:11
COPY build/libs/IJS-BE-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]