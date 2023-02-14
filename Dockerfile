FROM openjdk
COPY drones.db /home/
COPY target/musalasoft-0.0.1.jar /home/

ARG APPLICATION_ENV_VAR=stage
ENV APPLICATION_ENV=$APPLICATION_ENV_VAR

CMD ["java", "-jar", "/home/musalasoft-0.0.1.jar"]
EXPOSE 8080