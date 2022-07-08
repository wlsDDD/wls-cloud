### 创建docker镜像

```shell
docker build -f /data/Dockerfile -t wls-system:test
```

```shell
docker run -p 9090:9000 -p 9001:9001 --name minio \
-v /mydata/minio/data:/data \
-e MINIO_ROOT_USER=wlsadmin \
-e MINIO_ROOT_PASSWORD=wlsadmin@ \
-e MINIO_SERVER_URL=https://file.wls.plus \
-e MINIO_BROWSER_REDIRECT_URL=https://minio.wls.plus \
-d minio/minio server /data --console-address ":9001"
```