FROM java:8
LABEL maintainer="tijanic@gmail.com"
VOLUME /tmp
EXPOSE 8080
ADD target/url-shortener-0.0.1-SNAPSHOT.jar url-shortener-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","url-shortener-0.0.1-SNAPSHOT.jar"]