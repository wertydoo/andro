#Pulling from openjdk to get Java 14
FROM openjdk:14-jdk-alpine
#Metadata
LABEL author="Thomas Driscoll"
#Environment variables
ENV PORT=8019 \
    CONFIG_USER_NAME="developer" \
    CONFIG_PASSWORD="osai@illinois" \
    CONFIG_SERVER_URL="https://dev.config-server.thomas-driscoll.com/"
#Copies all source code in same folder - . - to docker virtual fs folder, /app
COPY . /app
#Working director where instructions will run
WORKDIR /app
#Allow Docker to write to files to host
VOLUME ["/app"]
#Run ./gradlew build
RUN ./gradlew build
#Port mapping - HOST_PORT:CONTAINER_PORT
EXPOSE 8019:8018
ENTRYPOINT ["java", "-jar", "/app/build/libs/andro-0.0.1-SNAPSHOT.jar"]
