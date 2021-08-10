FROM openjdk:8-jdk-alpine
ARG JAR_FILE=*.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-jar","/app.war"]
EXPOSE 8080
#FROM tomcat:8.5.30
#COPY . /usr/local/tomcat/webapps/
#EXPOSE 8080