FROM eclipse-temurin:17-jre-alpine
ADD target/vm-fabio-artuso-bank-app.jar vm-fabio-artuso-bank-app
ENTRYPOINT ["java", "-jar", "vm-fabio-artuso-bank-app"]

