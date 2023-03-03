#!/bin/bash

JAVA_APP=$1
JAVA_OPS=$2

stop() {
    echo "[stop] 开始停止 $JAVA_APP"
    pid=$(ps -ef | grep $JAVA_APP | grep -v grep | grep -v $$ | awk '{print $2}')
    if [ -n "$pid" ]; then
        echo "[stop] pid is $pid"
        kill -15 $pid
        echo "[stop] 停止 $JAVA_APP 中"
        # 等待最大 120 秒，直到关闭完成。
        for ((i = 0; i < 120; i++)); do
            pid=$(ps -ef | grep $JAVA_APP | grep -v grep | grep -v $$ | awk '{print $2}')
            if [ -n "$pid" ]; then
                echo -e "$i.\c"
            else
                echo
                echo "[stop] 正常停止 $JAVA_APP 成功"
                break
            fi
            sleep 1
        done
        # 如果正常关闭失败，那么进行强制 kill -9 进行关闭
        pid=$(ps -ef | grep $JAVA_APP | grep -v grep | grep -v $$ | awk '{print $2}')
        if [ -n "$pid" ]; then
            echo "正常 [stop] $JAVA_APP 失败 强制 kill -9 $pid"
            kill -9 $pid
            echo "强制 [stop] $JAVA_APP 成功"
        fi
    else
        echo "[stop] $JAVA_APP is not running"
    fi
}

start() {
    stop
    echo "[start] 开始启动 $JAVA_APP"
    BUILD_ID=dontKillMe nohup java -jar "$JAVA_APP" "$JAVA_OPS" >/dev/null 2>&1 &
    for ((i = 0; i < 60; i++)); do
        pid=$(ps -ef | grep $JAVA_APP | grep -v grep | grep -v $$ | awk '{print $2}')
        if [ -n "$pid" ]; then
            echo
            echo "[start] $JAVA_APP 启动完成 pid is $pid"
            exit 0
        else
            echo -e "$i.\c"
            sleep 1
        fi
    done
    # 健康检查未通过，则异常退出 shell 脚本，不继续部署。
    pid=$(ps -ef | grep $JAVA_APP | grep -v grep | grep -v $$ | awk '{print $2}')
    if [ -n "$pid" ]; then
        echo "[start] $JAVA_APP 启动完成 pid is $pid"
    else
        echo "[start] $JAVA_APP 未检测到进程，可能部署失败。查看日志，自行判断是否启动成功"
        exit 1
    fi
}

if [ ! "$JAVA_APP" ]; then
    echo "[deploy] 请输入应用名称"
else
    echo "[deploy] 开始部署 $JAVA_APP $JAVA_OPS"
    start
fi
