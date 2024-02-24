# Uygulama için JDK gerekli
FROM openjdk:17-jdk-alpine

# Bizim bu projemizdeki JAR dosyamızın, Docker içinde çalışacağı konumu
ARG JAR_FILE=target/*.jar

# JAR dosyasını root klasörüne bu isimle kopyala
COPY ./target/HRManagementSystem-0.0.1.jar app.jar
#COPY target/*.jar hrmanagementsystem.jar

# Linux ortamını güncelle
#CMD apt-get update-y

# projenin calisacaği iç port
EXPOSE 8080

# Uygulamamızın çalışmasını sağlıyoruz.
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ./target/HRManagementSystem-0.0.1.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]
