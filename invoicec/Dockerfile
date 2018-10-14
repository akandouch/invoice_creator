FROM maven
WORKDIR /tmp
COPY . .
RUN mvn package -DskipTests
EXPOSE 8080
CMD ["mvn", "spring-boot:run"]