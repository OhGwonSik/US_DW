# Multi-stage build for Spring Boot application

# Stage 1: Build
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /workspace

COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle* ./

RUN sed -i 's/\r$//' gradlew && chmod +x gradlew

# Copy source code
COPY src ./src

# Download dependencies
RUN ./gradlew dependencies --no-daemon || return 0

# Build the application (skip tests for faster builds)
RUN ./gradlew --no-daemon clean bootWar -x test

RUN BOOT_WAR="$(ls build/libs/*.war | grep -Ev 'plain|sources|javadoc' | head -n 1)" \
 && echo "Using boot jar: ${BOOT_WAR}" \
 && cp "${BOOT_WAR}" /workspace/app.war

# Stage 2: Runtime
FROM ubuntu:latest

# 필요한 패키지 설치
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk curl tzdata && \
    rm -rf /var/lib/apt/lists/*

# 타임존 설정
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# JAR 파일 복사
#ARG JAR_FILE=build/libs/US_DW.war
COPY --from=build /workspace/app.war app.war

# APM Agent 다운로드
# RUN curl -o /elastic-apm-agent.jar -L 'https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=co.elastic.apm&a=elastic-apm-agent&v=LATEST'
#COPY --from=docker.elastic.co/observability/apm-agent-java:latest /usr/agent/elastic-apm-agent.jar /elastic-apm-agent.jar

# APM 설정 파일 복사
#COPY src/main/resources/elasticapm.properties /elasticapm.properties

EXPOSE 8001

# APM Agent와 함께 실행
#ENTRYPOINT ["java", "-javaagent:/elastic-apm-agent.jar", "-Delastic.apm.config_file=/elasticapm.properties", "-jar", "/app.war"]
# elastic 관련 컨테이너 꺼 놓아서 수정함 (20251017 류승호)
ENTRYPOINT ["java", "-jar", "/app.war"]