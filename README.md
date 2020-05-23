# About the project
This project, developed by Andrei Pisponen, represents
a simple web service (based on the Spring Boot), which calculates 
sum of numbers in the POST requests and returns the result. 

There are two possibilities to run the project:
1. Using the Intellij IDEA
2. Using maven and Tomcat

# Running application using the Intellij IDEA
1. Open project in the Intellij IDEA and wait until it is synchronized
2. Open the maven view tab and run target: Plugins -> spring-boot:run
3. Wait until service has started. Endpoint will be available 
by URL: http://localhost:8080/sum

# Running application using the maven and Tomcat
Prerequisites:
- Installed Tomcat 9 server with active application manager service.
- Installed maven

1. Open maven configuration file located in `${maven.home}/conf/settings.xml`
or maven user configuration file located in `${user.home}/.m2/settings.xml` 
and add Tomcat server configuration:
```
<server>
    <id>TomcatServer</id>
    <username>[USER]</username>
    <password>[PASS]</password>
</server>
```
The `[USER]` and the `[PASS]` shall be replaced with the proper Tomcat 
application manager service's user credentials.
2. Open command line or terminal from the project root folder
3. Run the command: `mvnw tomcat7:deploy`
4. Wait until deployment will be finished. Endpoint will be available  
by URL: http://localhost:8080/solution/sum

To redeploy the package use command: `mvnw tomcat7:redeploy`

# Testing endpoints

For the testing endpoints, Apache JMeter shall be used.
It allows simulating multiple concurrent requests. 

1. Open Apache JMeter
2. Load tests from `Test_requests.jmx` if you run application from Intellij IDEA 
or `Test_requests_tomcat.jmx` if you are using tomcat. there is differnce in the 
URL only.
3. Run tests by pressing "Start" button (green arrow). During test, Apache JMeter 
will send 20 concurrent requests with random numbers and one request with "end" keyword 
after 2 seconds.  
4. Observe the test results in "View Results in Table" and "View Results Tree" sections.

"View Results in Table" is useful for finding the exact time, when requests were sent,
and observing the response time.

"View Results Tree" is useful for observing numbers, which were sent, and observing 
the response results.