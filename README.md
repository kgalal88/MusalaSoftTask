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
