# Etapa de build: usa Gradle con Java 17
FROM gradle:8.2.1-jdk17 AS build
WORKDIR /home/gradle/project
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

# Etapa de ejecución: solo el JAR y Java runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
