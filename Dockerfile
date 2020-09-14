FROM tronprotocol/centos7-jdk8

ENV BASE_DIR="/FullNode"
ENV JAR_NAME="tron-rosetta-api-1.0.0.jar"
ENV DATA_DIR="/data"
ENV LOG_DIR="/logs"

ENV NET_TYPE="mainnet"

ENV XMS="8G"
ENV XMX="8G"
ENV JVM_OPTIONS="-Xms$XMS -Xmx$XMX -XX:NewRatio=7 \
-XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xloggc:./logs/gc.log \
-XX:+PrintGCDateStamps -XX:+CMSParallelRemarkEnabled -XX:ReservedCodeCacheSize=256m \
-XX:+CMSScavengeBeforeRemark"

ENV TRON_OPTIONS=""

ADD . /tron-rosetta-api

RUN set -o errexit -o nounset \
    && mkdir -p $BASE_DIR $DATA_DIR $LOG_DIR \
    && cd $BASE_DIR \
    && ln -s $LOG_DIR logs \
    && ln -s $DATA_DIR data \
    && cd /tron-rosetta-api \
    && ./gradlew build -xtest -xcheck --refresh-dependencies \
    && mkdir -p $BASE_DIR/conf \
    && cp -r src/main/resources/net_conf/* $BASE_DIR/conf \
    && cd build/libs \
    && cp $JAR_NAME $BASE_DIR \
    && rm -rf ~/.gradle \
    && rm -rf /tron-rosetta-api

WORKDIR $BASE_DIR

COPY docker-entrypoint.sh $BASE_DIR

EXPOSE 8080

ENTRYPOINT ["./docker-entrypoint.sh"]
