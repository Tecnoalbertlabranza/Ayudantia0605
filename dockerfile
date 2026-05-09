# Contruccion de el archivo JAR con maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Ejecucion de la imagen con el .jar generado
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# se copia el jar desde la etapa de contruccion
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]