FROM eclipse-temurin:21
WORKDIR /app
RUN apt -q -qq update && \
  DEBIAN_FRONTEND=noninteractive apt install -y maven
COPY pom.xml .
COPY library/src ./library/src
COPY library/pom.xml ./library
COPY compiler/src ./compiler/src
COPY compiler/pom.xml ./compiler
RUN mvn install