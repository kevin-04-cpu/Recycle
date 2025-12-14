# Etapa 1: build
FROM gradle:8.5-jdk21 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

# Etapa 2: runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/recycle-0.0.1-SNAPSHOT.jar app.jar

# Puerto expuesto
EXPOSE 8080

# Variable de entorno para MongoDB Atlas
ENV SPRING_DATA_MONGODB_URI="mongodb+srv://kevin:7503260694pc@recycle-cluster.b43184c.mongodb.net/reciclaje"

# Arranque de la aplicación
ENTRYPOINT ["java","-jar","app.jar"]
    