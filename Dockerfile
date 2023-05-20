FROM openjdk:11-jdk-slim
WORKDIR app/
RUN apt -q -qq update && \
  DEBIAN_FRONTEND=noninteractive apt install -y apt-utils git maven make
COPY install_locallibs.sh .
RUN ./install_locallibs.sh
COPY src/ ./src/
COPY pom.xml .
RUN mvn install
