# JDK for application
FROM openjdk:17-jdk-alpine

# The location where the JAR file runs in Docker
ARG JAR_FILE=target/*.jar

# Copy the JAR file to the root folder with this name
COPY ./target/HRManagementSystem-0.0.1.jar app.jar
#COPY target/*.jar hrmanagementsystem.jar

# Update Linux environment
#CMD apt-get update-y

# internal port where the project will run on
EXPOSE 8080

# make the application work.
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ./target/HRManagementSystem-0.0.1.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]
