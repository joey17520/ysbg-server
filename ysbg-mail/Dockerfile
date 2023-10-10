FROM openjdk:17
LABEL authors="Joey"

# 指定工作目录
WORKDIR /app

# 将jar包添加到工作目录
ADD target/ysbg-mail-1.0-SNAPSHOT.jar .

# 暴露端口
EXPOSE 8082

# 启动命令
ENTRYPOINT ["java", "-jar", "/app/ysbg-mail-1.0-SNAPSHOT.jar"]