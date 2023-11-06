FROM tomcat:9.0
ADD target/jso-bo.war /usr/local/tomcat/webapps
ADD addressbook.csv /usr/local/tomcat
CMD ["npm i dotenv", "run"]
CMD ["catalina.sh", "run"]
