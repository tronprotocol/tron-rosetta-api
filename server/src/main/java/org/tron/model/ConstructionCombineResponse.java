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
 * ConstructionCombineResponse is returned by &#x60;/construction/combine&#x60;. The network payload will be sent directly to the &#x60;construction/submit&#x60; endpoint.
 */
@ApiModel(description = "ConstructionCombineResponse is returned by `/construction/combine`. The network payload will be sent directly to the `construction/submit` endpoint.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

public class ConstructionCombineResponse   {
  @JsonProperty("signed_transaction")
  private String signedTransaction;

  public ConstructionCombineResponse signedTransaction(String signedTransaction) {
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
    ConstructionCombineResponse constructionCombineResponse = (ConstructionCombineResponse) o;
    return Objects.equals(this.signedTransaction, constructionCombineResponse.signedTransaction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signedTransaction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstructionCombineResponse {\n");
    
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

