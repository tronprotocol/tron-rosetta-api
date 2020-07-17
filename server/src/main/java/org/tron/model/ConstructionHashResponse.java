package org.tron.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ConstructionHashResponse is the output of the &#x60;/construction/hash&#x60; endpoint.
 */
@ApiModel(description = "ConstructionHashResponse is the output of the `/construction/hash` endpoint.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

public class ConstructionHashResponse   {
  @JsonProperty("transaction_hash")
  private String transactionHash;

  public ConstructionHashResponse transactionHash(String transactionHash) {
    this.transactionHash = transactionHash;
    return this;
  }

  /**
   * Get transactionHash
   * @return transactionHash
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTransactionHash() {
    return transactionHash;
  }

  public void setTransactionHash(String transactionHash) {
    this.transactionHash = transactionHash;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConstructionHashResponse constructionHashResponse = (ConstructionHashResponse) o;
    return Objects.equals(this.transactionHash, constructionHashResponse.transactionHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionHash);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstructionHashResponse {\n");
    
    sb.append("    transactionHash: ").append(toIndentedString(transactionHash)).append("\n");
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

