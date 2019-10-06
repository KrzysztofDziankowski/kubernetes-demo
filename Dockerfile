FROM openjdk:12-alpine
COPY target/kubernetes-demo-0.0.1.jar kubernetes-demo.jar
EXPOSE 8080
ENTRYPOINT exec java ${JAVA_OPTS} -jar /kubernetes-demo.jar