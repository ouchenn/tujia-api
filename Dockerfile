FROM openjdk:11
EXPOSE 8080
ADD target/tujia-api.jar tujia-api.jar
ENTRYPOINT ["java","-jar","/tujia-api.jar"]