package org.tron.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.tron.model.Block;
import org.tron.model.TransactionIdentifier;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A BlockResponse includes a fully-populated block or a partially-populated block with a list of other transactions to fetch (other_transactions).
 */
@ApiModel(description = "A BlockResponse includes a fully-populated block or a partially-populated block with a list of other transactions to fetch (other_transactions).")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

public class BlockResponse   {
  @JsonProperty("block")
  private Block block;

  @JsonProperty("other_transactions")
  @Valid
  private List<TransactionIdentifier> otherTransactions = null;

  public BlockResponse block(Block block) {
    this.block = block;
    return this;
  }

  /**
   * Get block
   * @return block
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Block getBlock() {
    return block;
  }

  public void setBlock(Block block) {
    this.block = block;
  }

  public BlockResponse otherTransactions(List<TransactionIdentifier> otherTransactions) {
    this.otherTransactions = otherTransactions;
    return this;
  }

  public BlockResponse addOtherTransactionsItem(TransactionIdentifier otherTransactionsItem) {
    if (this.otherTransactions == null) {
      this.otherTransactions = new ArrayList<>();
    }
    this.otherTransactions.add(otherTransactionsItem);
    return this;
  }

  /**
   * Some blockchains may require additional transactions to be fetched that weren't returned in the block response (ex: block only returns transaction hashes). For blockchains with a lot of transactions in each block, this can be very useful as consumers can concurrently fetch all transactions returned.
   * @return otherTransactions
  */
  @ApiModelProperty(value = "Some blockchains may require additional transactions to be fetched that weren't returned in the block response (ex: block only returns transaction hashes). For blockchains with a lot of transactions in each block, this can be very useful as consumers can concurrently fetch all transactions returned.")

  @Valid

  public List<TransactionIdentifier> getOtherTransactions() {
    return otherTransactions;
  }

  public void setOtherTransactions(List<TransactionIdentifier> otherTransactions) {
    this.otherTransactions = otherTransactions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BlockResponse blockResponse = (BlockResponse) o;
    return Objects.equals(this.block, blockResponse.block) &&
        Objects.equals(this.otherTransactions, blockResponse.otherTransactions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(block, otherTransactions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BlockResponse {\n");
    
    sb.append("    block: ").append(toIndentedString(block)).append("\n");
    sb.append("    otherTransactions: ").append(toIndentedString(otherTransactions)).append("\n");
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

