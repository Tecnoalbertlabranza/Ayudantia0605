# ETAPA 1: Construcción (Generamos el .jar)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Imagen liviana)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos el jar desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]