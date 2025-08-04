FROM eclipse-temurin:21 AS app-build
ENV RELEASE=21

WORKDIR /opt/build
COPY target/*.jar application.jar

RUN java -Djarmode=tools -jar application.jar extract  --layers --launcher

RUN $JAVA_HOME/bin/jlink \
         --add-modules java.desktop,java.base,java.instrument,java.management,java.naming,java.security.jgss,java.net.http,java.sql,jdk.jfr,jdk.unsupported,jdk.crypto.ec \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=zip-2 \
         --output /opt/java-runtime

FROM debian:buster-slim

ENV JAVA_HOME=/opt/java-runtime
ENV PATH="${JAVA_HOME}/bin:${PATH}"

RUN groupadd --gid 1000 spring-app \
 && useradd --uid 1000 --gid spring-app --shell /bin/bash --create-home spring-app

USER spring-app:spring-app
WORKDIR /app

COPY --from=app-build /opt/java-runtime /opt/java-runtime
COPY --from=app-build /opt/build/application/dependencies/ ./
COPY --from=app-build /opt/build/application/snapshot-dependencies/ ./
COPY --from=app-build /opt/build/application/spring-boot-loader/ ./
COPY --from=app-build /opt/build/application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]