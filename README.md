# Airport DB

## Prerequisites
* JVM: Java 8
* Maven: 3.5.4+

## To build, run tests and run the application
Execute: __mvn clean install verify spring-boot:run__

## Usage
* The application will start-up a web-server on port 8080 on the local host.
* By navigating to http://localhost:8080 the airport search form will be displayed
* By providing a latitude, longitude and a radius (eg. latitude = 47.498, longitude = 19.04, radius = 500) the application will return the list of airports corresponding the given query
* The result list is ordered by distance in kilometers in ascending order