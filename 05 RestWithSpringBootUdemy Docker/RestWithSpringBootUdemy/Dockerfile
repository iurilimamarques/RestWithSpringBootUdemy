FROM openjdk:11-jdk-slim
VOLUME /tpm
ADD target/rest-with-springboot-udemy-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN bash -c  'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]