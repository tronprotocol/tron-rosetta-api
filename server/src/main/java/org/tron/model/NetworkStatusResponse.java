package org.tron.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.tron.model.BlockIdentifier;
import org.tron.model.Peer;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NetworkStatusResponse contains basic information about the node&#39;s view of a blockchain network. If a Rosetta implementation prunes historical state, it should populate the optional &#x60;oldest_block_identifier&#x60; field with the oldest block available to query. If this is not populated, it is assumed that the &#x60;genesis_block_identifier&#x60; is the oldest queryable block.
 */
@ApiModel(description = "NetworkStatusResponse contains basic information about the node's view of a blockchain network. If a Rosetta implementation prunes historical state, it should populate the optional `oldest_block_identifier` field with the oldest block available to query. If this is not populated, it is assumed that the `genesis_block_identifier` is the oldest queryable block.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

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
  @Valid
  private List<Peer> peers = new ArrayList<>();

  public NetworkStatusResponse currentBlockIdentifier(BlockIdentifier currentBlockIdentifier) {
    this.currentBlockIdentifier = currentBlockIdentifier;
    return this;
  }

  /**
   * Get currentBlockIdentifier
   * @return currentBlockIdentifier
  */
  @ApiModelProperty(required = true, value = "")
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
  */
  @ApiModelProperty(required = true, value = "")
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
  */
  @ApiModelProperty(required = true, value = "")
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
  */
  @ApiModelProperty(value = "")

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
    this.peers.add(peersItem);
    return this;
  }

  /**
   * Get peers
   * @return peers
  */
  @ApiModelProperty(required = true, value = "")
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
    return Objects.equals(this.currentBlockIdentifier, networkStatusResponse.currentBlockIdentifier) &&
        Objects.equals(this.currentBlockTimestamp, networkStatusResponse.currentBlockTimestamp) &&
        Objects.equals(this.genesisBlockIdentifier, networkStatusResponse.genesisBlockIdentifier) &&
        Objects.equals(this.oldestBlockIdentifier, networkStatusResponse.oldestBlockIdentifier) &&
        Objects.equals(this.peers, networkStatusResponse.peers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentBlockIdentifier, currentBlockTimestamp, genesisBlockIdentifier, oldestBlockIdentifier, peers);
  }

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

