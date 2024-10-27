# Stage 1: Build the project using Maven
FROM maven:3.8.4-openjdk-11 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package

# Stage 2: Deploy on Tomcat
FROM tomcat:9.0
COPY --from=build /app/target/webapp.war /usr/local/tomcat/webapps/