FROM maven:3.6.3-openjdk-15
RUN mkdir job4j_pooh
WORKDIR job4j_pooh
COPY . .
RUN mvn package -Dmaven.test.skip=true
EXPOSE 9000/tcp
CMD ["java", "-jar", "target/pooh.jar"]