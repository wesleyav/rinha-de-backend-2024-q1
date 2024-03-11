FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw && \
    ./mvnw dependency:resolve
COPY src ./src
EXPOSE 8080
CMD [ "./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=dev"]