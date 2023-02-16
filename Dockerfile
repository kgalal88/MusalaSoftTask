FROM openjdk
COPY drones.db /home/
COPY target/musalasoft-0.0.1.jar /home/

CMD ["java", "-jar", "/home/musalasoft-0.0.1.jar"]
EXPOSE 8080