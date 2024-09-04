FROM openjdk:17-alpine

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENV JAR_PATH=/app/build/libs

# 빌드 과정 중 불필요한 파일 삭제
RUN apt-get update && apt-get install -y --no-install-recommends \
    some-package \
    && rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Seoul","/app.jar"]