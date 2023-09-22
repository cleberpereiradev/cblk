FROM openjdk:17-alpine
ENV APP_NAME cblk
COPY ./target/cblk-0.0.1-SNAPSHOT.jar  /app/cblk-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD java -jar cblk-0.0.1-SNAPSHOT.jar.jar
EXPOSE 8080