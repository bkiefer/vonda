FROM openjdk:11-jdk-slim
WORKDIR app/
RUN apt -q -qq update && \
  DEBIAN_FRONTEND=noninteractive apt install -y apt-utils git maven make
COPY pom.xml dependencies.sh install_locallibs.sh ./
COPY src/ ./src/
RUN ./install_locallibs.sh
RUN mvn -q install
