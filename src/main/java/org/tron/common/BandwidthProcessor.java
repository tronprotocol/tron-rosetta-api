package org.tron.common;

import static org.tron.core.config.Parameter.ChainConstant.TRX_PRECISION;
import static org.tron.protos.Protocol.Transaction.Contract.ContractType.TransferContract;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.tron.core.ChainBaseManager;
import org.tron.core.Constant;
import org.tron.core.capsule.AccountCapsule;
import org.tron.core.capsule.TransactionCapsule;
import org.tron.core.config.Parameter;
import org.tron.core.config.Parameter.ChainConstant;
import org.tron.core.exception.ContractValidateException;
import org.tron.protos.Protocol.Transaction.Contract;
import org.tron.protos.contract.AssetIssueContractOuterClass.TransferAssetContract;
import org.tron.protos.contract.BalanceContract.TransferContract;


@Slf4j(topic = "DB")
public class BandwidthProcessor {
  protected long precision;
  protected long windowSize;
  protected long averageWindowSize;

  private ChainBaseManager chainBaseManager;

  public BandwidthProcessor(ChainBaseManager chainBaseManager) {
    this.precision = ChainConstant.PRECISION;
    this.windowSize = ChainConstant.WINDOW_SIZE_MS / ChainConstant.BLOCK_PRODUCED_INTERVAL;
    this.averageWindowSize =
        Parameter.AdaptiveResourceLimitConstants.PERIODS_MS / ChainConstant.BLOCK_PRODUCED_INTERVAL;
    this.chainBaseManager = chainBaseManager;
  }

  public long calculateConsume(TransactionCapsule trx) throws
      ContractValidateException {
       long bytesSize;
    if (chainBaseManager.getDynamicPropertiesStore().supportVM()) {
      bytesSize = trx.getInstance().toBuilder().clearRet().build().getSerializedSize();
    } else {
      bytesSize = trx.getSerializedSize();
    }

    List<Contract> contracts = trx.getInstance().getRawData().getContractList();
    for (Contract contract : contracts) {
      if (contract.getType() != TransferContract) {
        continue;
      }
      if (chainBaseManager.getDynamicPropertiesStore().supportVM()) {
        bytesSize += Constant.MAX_RESULT_SIZE_IN_TX;
      }

      AccountCapsule accountCapsule = chainBaseManager.getAccountStore().get(TransactionCapsule.getOwner(contract));
      if (accountCapsule == null) {
        throw new ContractValidateException("account does not exist");
      }

      long now = chainBaseManager.getHeadSlot();
      if (contractCreateNewAccount(contract)) {
        return consumeForCreateNewAccount(accountCapsule, bytesSize, now);
      }

      if (useAccountNet(accountCapsule, bytesSize, now)) {
        return 0;
      }

      if (useFreeNet(accountCapsule, bytesSize, now)) {
        return 0;
      }
      return chainBaseManager.getDynamicPropertiesStore().getTransactionFee() * bytesSize;
    }
    return 0;
  }

  private long consumeForCreateNewAccount(AccountCapsule accountCapsule, long bytes, long now) {
    boolean ret = consumeBandwidthForCreateNewAccount(accountCapsule, bytes, now);

    if (!ret) {
      return chainBaseManager.getDynamicPropertiesStore().getCreateAccountFee();
    }
    return 0;
  }

  private boolean consumeBandwidthForCreateNewAccount(AccountCapsule accountCapsule, long bytes,
                                                     long now) {
    long createNewAccountBandwidthRatio = chainBaseManager.getDynamicPropertiesStore()
        .getCreateNewAccountBandwidthRate();
    long netUsage = accountCapsule.getNetUsage();
    long latestConsumeTime = accountCapsule.getLatestConsumeTime();
    long netLimit = calculateGlobalNetLimit(accountCapsule);
    long newNetUsage = increase(netUsage, 0, latestConsumeTime, now);
    if (bytes * createNewAccountBandwidthRatio <= (netLimit - newNetUsage)) {
      return true;
    }
    return false;
  }

  private boolean contractCreateNewAccount(Contract contract) {
    AccountCapsule toAccount;
    switch (contract.getType()) {
      case AccountCreateContract:
        return true;
      case TransferContract:
        TransferContract transferContract;
        try {
          transferContract = contract.getParameter().unpack(TransferContract.class);
        } catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
        }
        toAccount =
            chainBaseManager.getAccountStore().get(transferContract.getToAddress().toByteArray());
        return toAccount == null;
      case TransferAssetContract:
        TransferAssetContract transferAssetContract;
        try {
          transferAssetContract = contract.getParameter().unpack(TransferAssetContract.class);
        } catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
        }
        toAccount = chainBaseManager.getAccountStore()
            .get(transferAssetContract.getToAddress().toByteArray());
        return toAccount == null;
      default:
        return false;
    }
  }

  private long calculateGlobalNetLimit(AccountCapsule accountCapsule) {
    long frozeBalance = accountCapsule.getAllFrozenBalanceForBandwidth();
    if (frozeBalance < TRX_PRECISION) {
      return 0;
    }
    long netWeight = frozeBalance / TRX_PRECISION;
    long totalNetLimit = chainBaseManager.getDynamicPropertiesStore().getTotalNetLimit();
    long totalNetWeight = chainBaseManager.getDynamicPropertiesStore().getTotalNetWeight();
    if (totalNetWeight == 0) {
      return 0;
    }
    return (long) (netWeight * ((double) totalNetLimit / totalNetWeight));
  }

  private boolean useAccountNet(AccountCapsule accountCapsule, long bytes, long now) {
    long netUsage = accountCapsule.getNetUsage();
    long latestConsumeTime = accountCapsule.getLatestConsumeTime();
    long netLimit = calculateGlobalNetLimit(accountCapsule);
    long newNetUsage = increase(netUsage, 0, latestConsumeTime, now);

    if (bytes > (netLimit - newNetUsage)) {
      logger.debug("net usage is running out, now use free net usage");
      return false;
    }
    return true;
  }

  private boolean useFreeNet(AccountCapsule accountCapsule, long bytes, long now) {

    long freeNetLimit = chainBaseManager.getDynamicPropertiesStore().getFreeNetLimit();
    long freeNetUsage = accountCapsule.getFreeNetUsage();
    long latestConsumeFreeTime = accountCapsule.getLatestConsumeFreeTime();
    long newFreeNetUsage = increase(freeNetUsage, 0, latestConsumeFreeTime, now);

    if (bytes > (freeNetLimit - newFreeNetUsage)) {
      return false;
    }

    long publicNetLimit = chainBaseManager.getDynamicPropertiesStore().getPublicNetLimit();
    long publicNetUsage = chainBaseManager.getDynamicPropertiesStore().getPublicNetUsage();
    long publicNetTime = chainBaseManager.getDynamicPropertiesStore().getPublicNetTime();

    long newPublicNetUsage = increase(publicNetUsage, 0, publicNetTime, now);

    if (bytes > (publicNetLimit - newPublicNetUsage)) {
      return false;
    }
    return true;

  }

  private long getUsage(long usage, long windowSize) {
    return usage * windowSize / precision;
  }

  private long divideCeil(long numerator, long denominator) {
    return (numerator / denominator) + ((numerator % denominator) > 0 ? 1 : 0);
  }

  private long increase(long lastUsage, long usage, long lastTime, long now) {
    return increase(lastUsage, usage, lastTime, now, windowSize);
  }

  private long increase(long lastUsage, long usage, long lastTime, long now, long windowSize) {
    long averageLastUsage = divideCeil(lastUsage * precision, windowSize);
    long averageUsage = divideCeil(usage * precision, windowSize);

    if (lastTime != now) {
      assert now > lastTime;
      if (lastTime + windowSize > now) {
        long delta = now - lastTime;
        double decay = (windowSize - delta) / (double) windowSize;
        averageLastUsage = Math.round(averageLastUsage * decay);
      } else {
        averageLastUsage = 0;
      }
    }
    averageLastUsage += averageUsage;
    return getUsage(averageLastUsage, windowSize);
  }

}


