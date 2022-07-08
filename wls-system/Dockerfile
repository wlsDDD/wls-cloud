# 基础镜像
FROM  openjdk:8-jre
# author
MAINTAINER wls
# 创建目录
RUN mkdir -p /data/wls-cloud/docker/wls-system
# 指定路径
WORKDIR /data/wls-cloud/docker/wls-system
# 挂载目录
VOLUME /data/wls-cloud/docker/wls-system
# 复制jar文件到路径
COPY /var/lib/jenkins/workspace/wls-cloud-system/wls-system/target/wls-system.jar /data/wls-cloud/docker/wls-system/wls-system.jar

# 启动系统服务
ENTRYPOINT ["java","-jar","wls-system.jar"]



