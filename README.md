# MusalaTask

This microservice is done using Spring Boot and SQLLite DB (drones.db) which is preloaded and pushed to this repo.

Please use the below commands to build/run the projects:

- mvn clean install
- java -jar target/musalasoft-0.0.1.jar

And the below commands to build/run the docker image
- docker build -t my-musalasoft-task .
- docker run -d -p 8080:8080 my-musalasoft-task

After you build/run the project, please use the below swagger URL to test the microservice
- http://localhost:8080/swagger-ui/

The microdervice contains the below API endpoints

# drones-controller Drones Controller

GET 
​/drones​/
getDrones
- http://localhost:8080/drones/
- http://localhost:8080/drones/?serialNumber=123456780
- http://localhost:8080/drones/?state=LOADED

POST
​/drones​/register
registerDrone
- http://localhost:8080/drones/register

PUT
​/drones​/load
loadDrone
- http://localhost:8080/drones/load?serialNumber=123456780&codes=22&codes=11

PUT
​/drones​/unload
unloadDrone
- http://localhost:8080/drones/unload?serialNumber=123456780

# medications-controller Medications Controller
GET
​/medications​/
getMedications
- http://localhost:8080/medications/
