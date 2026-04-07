FROM maven:3.8-openjdk-17 AS Build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTest

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app


COPY --from=build /app/target/*.jar /app.jar

EXPOSE 8083

CMD ["java","-jar","/app/bff-agendador-tarefas.jar"]