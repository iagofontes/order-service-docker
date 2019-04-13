FROM openjdk:8
ADD target/order-service-docker.jar order-service-docker.jar
EXPOSE 8080
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-jar","order-service-docker.jar"]
