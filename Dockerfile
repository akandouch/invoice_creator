FROM maven:3-jdk-8-alpine
VOLUME /tmp
COPY . .
RUN mvn clean install
RUN cp ./target/invoicer-backend.jar ./invoicer-backend.sh
RUN chmod a+x ./invoicer-backend.sh
EXPOSE 8080
ENTRYPOINT ["./invoicer-backend.sh"]
