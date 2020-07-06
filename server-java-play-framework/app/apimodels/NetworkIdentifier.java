package apimodels;

import apimodels.SubNetworkIdentifier;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * The network_identifier specifies which network a particular object is associated with.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class NetworkIdentifier   {
  @JsonProperty("blockchain")
  private String blockchain;

  @JsonProperty("network")
  private String network;

  @JsonProperty("sub_network_identifier")
  private SubNetworkIdentifier subNetworkIdentifier;

  public NetworkIdentifier blockchain(String blockchain) {
    this.blockchain = blockchain;
    return this;
  }

   /**
   * Get blockchain
   * @return blockchain
  **/
  @NotNull
  public String getBlockchain() {
    return blockchain;
  }

  public void setBlockchain(String blockchain) {
    this.blockchain = blockchain;
  }

  public NetworkIdentifier network(String network) {
    this.network = network;
    return this;
  }

   /**
   * If a blockchain has a specific chain-id or network identifier, it should go in this field. It is up to the client to determine which network-specific identifier is mainnet or testnet.
   * @return network
  **/
  @NotNull
  public String getNetwork() {
    return network;
  }

  public void setNetwork(String network) {
    this.network = network;
  }

  public NetworkIdentifier subNetworkIdentifier(SubNetworkIdentifier subNetworkIdentifier) {
    this.subNetworkIdentifier = subNetworkIdentifier;
    return this;
  }

   /**
   * Get subNetworkIdentifier
   * @return subNetworkIdentifier
  **/
  @Valid
  public SubNetworkIdentifier getSubNetworkIdentifier() {
    return subNetworkIdentifier;
  }

  public void setSubNetworkIdentifier(SubNetworkIdentifier subNetworkIdentifier) {
    this.subNetworkIdentifier = subNetworkIdentifier;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NetworkIdentifier networkIdentifier = (NetworkIdentifier) o;
    return Objects.equals(blockchain, networkIdentifier.blockchain) &&
        Objects.equals(network, networkIdentifier.network) &&
        Objects.equals(subNetworkIdentifier, networkIdentifier.subNetworkIdentifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blockchain, network, subNetworkIdentifier);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NetworkIdentifier {\n");
    
    sb.append("    blockchain: ").append(toIndentedString(blockchain)).append("\n");
    sb.append("    network: ").append(toIndentedString(network)).append("\n");
    sb.append("    subNetworkIdentifier: ").append(toIndentedString(subNetworkIdentifier)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

