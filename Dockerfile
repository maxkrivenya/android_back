FROM openjdk
ENV TZ="Europe/Minsk"

RUN groupadd myra && useradd myra -g myra
RUN install -d -m 0755 -o myra -g myra /apihell/service

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src
RUN ./mvnw -B -q package

USER myra
WORKDIR /pms_back/service

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/target/pms_back-0.0.1-SNAPSHOT.jar"]