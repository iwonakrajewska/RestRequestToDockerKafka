FROM openjdk:8-jre-alpine
WORKDIR /app
COPY   ./target/RestRequestToDockerKafka.jar /app 
EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar RestRequestToDockerKafka.jar"] 



