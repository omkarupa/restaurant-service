FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/restaurant-service.jar restaurant-service.jar

EXPOSE 8091

ENTRYPOINT ["java", "-jar", "restaurant-service.jar"]