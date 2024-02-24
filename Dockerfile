#基础镜像
FROM eclipse-temurin:8
#作者
MAINTAINER ljw
#工作目录
WORKDIR /usr/local/java
#同步docker内部的时间
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ /etc/timeznoe
#设置时区
ENV TZ=Asia/Shanghai
EXPOSE 8080
#复制jar包到user/local/java下
ARG JAR_FILE
ADD ${JAR_FILE} ./san-bu-wu-shi.jar

ENTRYPOINT ["nohup","java","-Dspring.config.location=/usr/local/java/application.yml,/usr/local/java/application-dev.yml","-jar","/usr/local/java/san-bu-wu-shi.jar",">","/usr/local/java/san-bu-wu-shi.log","&>","&"]



