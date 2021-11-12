FROM tomcat:9.0
ADD target/address-book.war /usr/local/tomcat/webapps
ADD addressbook.csv /usr/local/tomcat
ADD addresses.ftl /usr/local/tomcat
CMD ["catalina.sh", "run"]