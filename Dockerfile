FROM tronprotocol/centos7-jdk8

ENV BASE_DIR="/data"
ENV JARNAME="tron-rosetta-api-1.0.0.jar"
ENV DATADIR="chain"

RUN set -o errexit -o nounset \
    && yum -y install git \
    && mkdir -p $BASE_DIR \
    && echo "git clone" \
    && cd / \
    && git clone https://github.com/tronprotocol/tron-rosetta-api.git \
    && cd tron-rosetta-api \
    && ./gradlew build -x test \
    && cd build/libs \
    && cp $JARNAME $BASE_DIR \
    && rm -rf ~/.gradle \
    && rm -rf /tron-rosetta-api

WORKDIR $BASE_DIR

EXPOSE 8080

CMD ["sh", "-c", "java -jar $JARNAME -d chain"]