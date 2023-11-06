jso-bo-app
------------
<p>in git bash</p>
<p>git clone https://github.com/CharlieSwires/jso-bo-app</p>

<p>This contains both the java and React</p>

build
-----
<p>cd jso-bo-app/src/main/webapp/jso-bo-app</p>
<p>npm run build</p>
<p>cd ../../../..</p>
<p>you'll need an application.properties file with the DB username and password
URL</p>
<p>mvn package</p>

<p>produces jso-bo-app.war in target</p>

<p>7zip jso-bo-app.war</p>
<p>remove the jso-bo-app/node-nodules</p>

deploy
------
<p>docker build --tag jso:latest .</p>
<p>docker run  --env-file ./env.list --name container3 -d -p 8881:8080 jso:latest</p>


browser
-------
<p>http://localhost:8881/jso-bo/jso-bo-app/build/index.html</p>

RESTful
-------

<p>http://localhost:8881/jso-bo/JSOEntry/add</p>
<p>http://localhost:8881/jso-bo/JSOEntry/delete/{firstName}/{lastName}</p>
<p>http://localhost:8881/jso-bo/JSOEntry/get/{firstName}/{lastName}</p>
<p>http://localhost:8881/jso-bo/JSOEntry/get/{lastName} -- returns list of JSON</p>
<p>http://localhost:8881/jso-bo/JSOEntry/getFirst/{firstname} -- returns list of JSON</p>
<p>http://localhost:8881/jso-bo/JSOEntry/getAllCSV -- returns CSV file</p>
<p>http://localhost:8881/jso-bo/JSOEntry/getAll -- returns list of JSON</p>
<p>http://localhost:8881/jso-bo/JSOEntry/loadAll -- loads db from addressbook.csv</p>

