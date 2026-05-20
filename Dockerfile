## USA JAVA 21 YA INSTALADO
#FROM eclipse-temurin:21-jdk
#
## CARPETA INTERNA DEL CONTENEDOR
#WORKDIR /app
#
## COPIAR EL JAR AL CONTENEDOR
#COPY target/basket-love-api-0.0.1-SNAPSHOT.jar app.jar
#
## INFORMAR PUERTO
#EXPOSE 8080
#
## COMANDO DE ARRANQUE
#ENTRYPOINT ["java", "-jar", "app.jar"]

FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]