package org.tron.invoker;

import com.beust.jcommander.internal.Lists;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.math.BigInteger;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.tron.api.GrpcAPI;
import org.tron.api.WalletGrpc;
import org.tron.common.crypto.ECKey;
import org.tron.common.utils.Utils;
import org.tron.core.Wallet;
import org.tron.core.config.args.Args;
import org.tron.protos.Protocol;
import org.tron.protos.contract.BalanceContract;

public class GenerateTransactionTest {

  private ManagedChannel channelFull = null;
  private WalletGrpc.WalletBlockingStub blockingStubFull = null;

  private final static String pk1 = "22a6aca17f8ec257cc57e190902767d7fedf908bba920b4fbeaab8f158e0da17";
  private final static String pk2 = "b6d8d3382c32d4d066c4f830a7e53c3da9ad8b9665dda4ca081b6cd4e807d09c";
  private final static String pk3 = "03caf867c46aaf86d56aa446db80cb49305126b77bfaccfe57ab17bdb4993ccc";
  private final static String pk4 = "763009595dd132aaf2d248999f2c6e7ba0acbbd9a9dfd88f7c2c158d97327645";
  private final static String pk5 = "a21a3074d4d84685efaffcd7c04e3eccb541ec4c85f61c41a099cd598ad39825";
  private final static String pk6 = "541a2d585fcea7e9b1803df4eb49af0eb09f1fa2ce06aa5b8ed60ac95655d66d";
  private final static String pk7 = "7d5a7396d6430edb7f66aa5736ef388f2bea862c9259de8ad8c2cfe080f6f5a0";
  private final static String pk8 = "7c4977817417495f4ca0c35ab3d5a25e247355d68f89f593f3fea2ab62c8644f";

  private static byte[] add1 = getFinalAddress(pk1);
  private static byte[] add2 = getFinalAddress(pk2);
  private static byte[] add3 = getFinalAddress(pk3);
  private static byte[] add4 = getFinalAddress(pk4);
  private static byte[] add5 = getFinalAddress(pk5);
  private static byte[] add6 = getFinalAddress(pk6);
  private static byte[] add7 = getFinalAddress(pk7);
  private static byte[] add8 = getFinalAddress(pk8);

  private List<byte[]> addrList = Lists.newArrayList();

  @Before
  public void init(){
//    String fullnode = String.format("%s:%d", "127.0.0.1",
//            Args.getInstance().getRpcPort());
    String fullnode = String.format("%s:%d", "127.0.0.1",
            50051);
    channelFull = ManagedChannelBuilder.forTarget(fullnode)
            .usePlaintext(true)
            .build();
    blockingStubFull = WalletGrpc.newBlockingStub(channelFull);
    addrList.add(add1);
    addrList.add(add2);
    addrList.add(add3);
    addrList.add(add4);
    addrList.add(add5);
    addrList.add(add6);
    addrList.add(add7);
    addrList.add(add8);
  }

  @Test
  public void generateSomeTransactions() {
    int during = 1*1000; // ms
    int runTime = 0;
    int sleepOnce = 100;
    int count = 0;
    while (true) {
      int index = count % 8;
//      ECKey ecKey2 = new ECKey(Utils.getRandom());
//      byte[] address = ecKey2.getAddress();
      String sunPri = "cba92a516ea09f620a16ff7ee95ce0df1d56550a8babe9964981a7144c8a784a";
      byte[] sunAddress = getFinalAddress(sunPri);
      sendcoin(addrList.get(index), 1_000_000L, sunAddress, sunPri, blockingStubFull);
      try {
        Thread.sleep(sleepOnce);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
//      if ((runTime += sleepOnce) > during) {
//        return;
//      }
    }
  }


  /**
   * Set public for future use.
   * @param priKey private key
   * @return public addr
   */
  public static byte[] getFinalAddress(String priKey) {
    Wallet.setAddressPreFixByte((byte) 0x41);
    ECKey key = ECKey.fromPrivate(new BigInteger(priKey, 16));
    return key.getAddress();
  }

  public static Boolean sendcoin(byte[] to, long amount, byte[] owner, String priKey,
                                 WalletGrpc.WalletBlockingStub blockingStubFull) {
    Wallet.setAddressPreFixByte((byte) 0x41);
    ECKey temKey = null;
    try {
      BigInteger priK = new BigInteger(priKey, 16);
      temKey = ECKey.fromPrivate(priK);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    final ECKey ecKey = temKey;

    int times = 0;
    while (times++ <= 2) {

      BalanceContract.TransferContract.Builder builder =
              BalanceContract.TransferContract.newBuilder();
      ByteString bsTo = ByteString.copyFrom(to);
      ByteString bsOwner = ByteString.copyFrom(owner);
      builder.setToAddress(bsTo);
      builder.setOwnerAddress(bsOwner);
      builder.setAmount(amount);

      BalanceContract.TransferContract contract = builder.build();
      Protocol.Transaction transaction = blockingStubFull.createTransaction(contract);
      if (transaction == null || transaction.getRawData().getContractCount() == 0) {
        continue;
      }
      transaction = signTransaction(ecKey, transaction);
      GrpcAPI.Return response = broadcastTransaction(transaction, blockingStubFull);
      return response.getResult();
    }
    return false;
  }

  /**
   * Set public for future use.
   * @param ecKey ecKey of the private key
   * @param transaction transaction object
   */
  public static Protocol.Transaction signTransaction(ECKey ecKey,
                                                     Protocol.Transaction transaction) {
    if (ecKey == null || ecKey.getPrivKey() == null) {
      return null;
    }
    transaction = TransactionUtils.setTimestamp(transaction);
    return TransactionUtils.sign(transaction, ecKey);
  }

  /**
   * Set public for future use.
   * @param transaction transaction object
   * @param blockingStubFull Grpc interface
   */
  public static GrpcAPI.Return broadcastTransaction(
          Protocol.Transaction transaction, WalletGrpc.WalletBlockingStub blockingStubFull) {
    int i = 10;
    GrpcAPI.Return response = blockingStubFull.broadcastTransaction(transaction);
    while (!response.getResult() && response.getCode() == GrpcAPI.Return.response_code.SERVER_BUSY
            && i > 0) {
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      i--;
      response = blockingStubFull.broadcastTransaction(transaction);
    }
    return response;
  }

}
