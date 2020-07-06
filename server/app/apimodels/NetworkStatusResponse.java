package apimodels;

import apimodels.BlockIdentifier;
import apimodels.Peer;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * NetworkStatusResponse contains basic information about the node&#39;s view of a blockchain network. If a Rosetta implementation prunes historical state, it should populate the optional &#x60;oldest_block_identifier&#x60; field with the oldest block available to query. If this is not populated, it is assumed that the &#x60;genesis_block_identifier&#x60; is the oldest queryable block.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class NetworkStatusResponse   {
  @JsonProperty("current_block_identifier")
  private BlockIdentifier currentBlockIdentifier;

  @JsonProperty("current_block_timestamp")
  private java.sql.Timestamp currentBlockTimestamp;

  @JsonProperty("genesis_block_identifier")
  private BlockIdentifier genesisBlockIdentifier;

  @JsonProperty("oldest_block_identifier")
  private BlockIdentifier oldestBlockIdentifier;

  @JsonProperty("peers")
  private List<Peer> peers = new ArrayList<>();

  public NetworkStatusResponse currentBlockIdentifier(BlockIdentifier currentBlockIdentifier) {
    this.currentBlockIdentifier = currentBlockIdentifier;
    return this;
  }

   /**
   * Get currentBlockIdentifier
   * @return currentBlockIdentifier
  **/
  @NotNull
@Valid
  public BlockIdentifier getCurrentBlockIdentifier() {
    return currentBlockIdentifier;
  }

  public void setCurrentBlockIdentifier(BlockIdentifier currentBlockIdentifier) {
    this.currentBlockIdentifier = currentBlockIdentifier;
  }

  public NetworkStatusResponse currentBlockTimestamp(java.sql.Timestamp currentBlockTimestamp) {
    this.currentBlockTimestamp = currentBlockTimestamp;
    return this;
  }

   /**
   * Get currentBlockTimestamp
   * @return currentBlockTimestamp
  **/
  @NotNull
@Valid
  public java.sql.Timestamp getCurrentBlockTimestamp() {
    return currentBlockTimestamp;
  }

  public void setCurrentBlockTimestamp(java.sql.Timestamp currentBlockTimestamp) {
    this.currentBlockTimestamp = currentBlockTimestamp;
  }

  public NetworkStatusResponse genesisBlockIdentifier(BlockIdentifier genesisBlockIdentifier) {
    this.genesisBlockIdentifier = genesisBlockIdentifier;
    return this;
  }

   /**
   * Get genesisBlockIdentifier
   * @return genesisBlockIdentifier
  **/
  @NotNull
@Valid
  public BlockIdentifier getGenesisBlockIdentifier() {
    return genesisBlockIdentifier;
  }

  public void setGenesisBlockIdentifier(BlockIdentifier genesisBlockIdentifier) {
    this.genesisBlockIdentifier = genesisBlockIdentifier;
  }

  public NetworkStatusResponse oldestBlockIdentifier(BlockIdentifier oldestBlockIdentifier) {
    this.oldestBlockIdentifier = oldestBlockIdentifier;
    return this;
  }

   /**
   * Get oldestBlockIdentifier
   * @return oldestBlockIdentifier
  **/
  @Valid
  public BlockIdentifier getOldestBlockIdentifier() {
    return oldestBlockIdentifier;
  }

  public void setOldestBlockIdentifier(BlockIdentifier oldestBlockIdentifier) {
    this.oldestBlockIdentifier = oldestBlockIdentifier;
  }

  public NetworkStatusResponse peers(List<Peer> peers) {
    this.peers = peers;
    return this;
  }

  public NetworkStatusResponse addPeersItem(Peer peersItem) {
    peers.add(peersItem);
    return this;
  }

   /**
   * Get peers
   * @return peers
  **/
  @NotNull
@Valid
  public List<Peer> getPeers() {
    return peers;
  }

  public void setPeers(List<Peer> peers) {
    this.peers = peers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NetworkStatusResponse networkStatusResponse = (NetworkStatusResponse) o;
    return Objects.equals(currentBlockIdentifier, networkStatusResponse.currentBlockIdentifier) &&
        Objects.equals(currentBlockTimestamp, networkStatusResponse.currentBlockTimestamp) &&
        Objects.equals(genesisBlockIdentifier, networkStatusResponse.genesisBlockIdentifier) &&
        Objects.equals(oldestBlockIdentifier, networkStatusResponse.oldestBlockIdentifier) &&
        Objects.equals(peers, networkStatusResponse.peers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentBlockIdentifier, currentBlockTimestamp, genesisBlockIdentifier, oldestBlockIdentifier, peers);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NetworkStatusResponse {\n");
    
    sb.append("    currentBlockIdentifier: ").append(toIndentedString(currentBlockIdentifier)).append("\n");
    sb.append("    currentBlockTimestamp: ").append(toIndentedString(currentBlockTimestamp)).append("\n");
    sb.append("    genesisBlockIdentifier: ").append(toIndentedString(genesisBlockIdentifier)).append("\n");
    sb.append("    oldestBlockIdentifier: ").append(toIndentedString(oldestBlockIdentifier)).append("\n");
    sb.append("    peers: ").append(toIndentedString(peers)).append("\n");
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

