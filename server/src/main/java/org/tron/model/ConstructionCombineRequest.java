package org.tron.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.tron.model.NetworkIdentifier;
import org.tron.model.Signature;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ConstructionCombineRequest is the input to the &#x60;/construction/combine&#x60; endpoint. It contains the unsigned transaction blob returned by &#x60;/construction/payloads&#x60; and all required signatures to create a network transaction.
 */
@ApiModel(description = "ConstructionCombineRequest is the input to the `/construction/combine` endpoint. It contains the unsigned transaction blob returned by `/construction/payloads` and all required signatures to create a network transaction.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

public class ConstructionCombineRequest   {
  @JsonProperty("network_identifier")
  private NetworkIdentifier networkIdentifier;

  @JsonProperty("unsigned_transaction")
  private String unsignedTransaction;

  @JsonProperty("signatures")
  @Valid
  private List<Signature> signatures = new ArrayList<>();

  public ConstructionCombineRequest networkIdentifier(NetworkIdentifier networkIdentifier) {
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

  public ConstructionCombineRequest unsignedTransaction(String unsignedTransaction) {
    this.unsignedTransaction = unsignedTransaction;
    return this;
  }

  /**
   * Get unsignedTransaction
   * @return unsignedTransaction
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getUnsignedTransaction() {
    return unsignedTransaction;
  }

  public void setUnsignedTransaction(String unsignedTransaction) {
    this.unsignedTransaction = unsignedTransaction;
  }

  public ConstructionCombineRequest signatures(List<Signature> signatures) {
    this.signatures = signatures;
    return this;
  }

  public ConstructionCombineRequest addSignaturesItem(Signature signaturesItem) {
    this.signatures.add(signaturesItem);
    return this;
  }

  /**
   * Get signatures
   * @return signatures
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Signature> getSignatures() {
    return signatures;
  }

  public void setSignatures(List<Signature> signatures) {
    this.signatures = signatures;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConstructionCombineRequest constructionCombineRequest = (ConstructionCombineRequest) o;
    return Objects.equals(this.networkIdentifier, constructionCombineRequest.networkIdentifier) &&
        Objects.equals(this.unsignedTransaction, constructionCombineRequest.unsignedTransaction) &&
        Objects.equals(this.signatures, constructionCombineRequest.signatures);
  }

  @Override
  public int hashCode() {
    return Objects.hash(networkIdentifier, unsignedTransaction, signatures);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstructionCombineRequest {\n");
    
    sb.append("    networkIdentifier: ").append(toIndentedString(networkIdentifier)).append("\n");
    sb.append("    unsignedTransaction: ").append(toIndentedString(unsignedTransaction)).append("\n");
    sb.append("    signatures: ").append(toIndentedString(signatures)).append("\n");
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

