FROM openjdk:17
WORKDIR /opt
EXPOSE 8080
COPY target/*.jar /opt/"INSERT JAR FILE NAME"
CMD ["java","-jar","INSERT JAR FILE NAME"]