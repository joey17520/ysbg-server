FROM openjdk:17
LABEL authors="Joey"

# 指定工作目录
WORKDIR /app

# 将jar包添加到工作目录
ADD target/ysbg-server-1.0-SNAPSHOT-exec.jar .

# 暴露端口
EXPOSE 8088

# 启动命令
ENTRYPOINT ["java", "-jar", "/app/ysbg-server-1.0-SNAPSHOT-exec.jar"]