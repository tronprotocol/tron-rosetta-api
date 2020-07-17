package org.tron.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.tron.model.NetworkIdentifier;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The transaction submission request includes a signed transaction.
 */
@ApiModel(description = "The transaction submission request includes a signed transaction.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

public class ConstructionSubmitRequest   {
  @JsonProperty("network_identifier")
  private NetworkIdentifier networkIdentifier;

  @JsonProperty("signed_transaction")
  private String signedTransaction;

  public ConstructionSubmitRequest networkIdentifier(NetworkIdentifier networkIdentifier) {
    this.networkIdentifier = networkIdentifier;
    return this;
  }

  /**
   * Get networkIdentifier
   * @return networkIdentifier
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public NetworkIdentifier getNetworkIdentifier() {
    return networkIdentifier;
  }

  public void setNetworkIdentifier(NetworkIdentifier networkIdentifier) {
    this.networkIdentifier = networkIdentifier;
  }

  public ConstructionSubmitRequest signedTransaction(String signedTransaction) {
    this.signedTransaction = signedTransaction;
    return this;
  }

  /**
   * Get signedTransaction
   * @return signedTransaction
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getSignedTransaction() {
    return signedTransaction;
  }

  public void setSignedTransaction(String signedTransaction) {
    this.signedTransaction = signedTransaction;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConstructionSubmitRequest constructionSubmitRequest = (ConstructionSubmitRequest) o;
    return Objects.equals(this.networkIdentifier, constructionSubmitRequest.networkIdentifier) &&
        Objects.equals(this.signedTransaction, constructionSubmitRequest.signedTransaction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(networkIdentifier, signedTransaction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstructionSubmitRequest {\n");
    
    sb.append("    networkIdentifier: ").append(toIndentedString(networkIdentifier)).append("\n");
    sb.append("    signedTransaction: ").append(toIndentedString(signedTransaction)).append("\n");
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

