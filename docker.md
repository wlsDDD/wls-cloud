### 创建docker镜像

```shell
docker build -f /var/lib/jenkins/workspace/wls-cloud-system/wls-system/Dockerfile -t wls-system:test .

```

### 创建容器

```shell
docker run --name wls-system -p 9100:9100 \
-e SPRING_PROFILES_ACTIVE=dev \
-d wls-system:test

```

