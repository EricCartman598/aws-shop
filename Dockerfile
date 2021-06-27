FROM tomcat:8.5.30
COPY . /usr/local/tomcat/webapps/
EXPOSE 8080
EXPOSE 2000/udp