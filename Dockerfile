FROM tronprotocol/centos7-jdk8

ENV BASE_DIR="/data"
ENV JARNAME="tron-rosetta-api-1.0.0.jar"
ENV DATADIR="chain"

ENV NET_TYPE="mainnet"

ADD . /tron-rosetta-api

RUN set -o errexit -o nounset \
    && mkdir -p $BASE_DIR \
    && cd /tron-rosetta-api \
    && ./gradlew build -xtest -xcheck --refresh-dependencies \
    && mkdir =p $BASE_DIR/conf \
    && cp -r src/main/resources/net_conf/* $BASE_DIR/conf \
    && cd build/libs \
    && cp $JARNAME $BASE_DIR \
    && rm -rf ~/.gradle \
    && rm -rf /tron-rosetta-api

WORKDIR $BASE_DIR

COPY docker-entrypoint.sh $BASE_DIR

EXPOSE 8080

ENTRYPOINT ["./docker-entrypoint.sh"]
