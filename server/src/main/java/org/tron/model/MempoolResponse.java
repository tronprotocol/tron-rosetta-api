package org.tron.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.tron.model.TransactionIdentifier;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A MempoolResponse contains all transaction identifiers in the mempool for a particular network_identifier.
 */
@ApiModel(description = "A MempoolResponse contains all transaction identifiers in the mempool for a particular network_identifier.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

public class MempoolResponse   {
  @JsonProperty("transaction_identifiers")
  @Valid
  private List<TransactionIdentifier> transactionIdentifiers = new ArrayList<>();

  public MempoolResponse transactionIdentifiers(List<TransactionIdentifier> transactionIdentifiers) {
    this.transactionIdentifiers = transactionIdentifiers;
    return this;
  }

  public MempoolResponse addTransactionIdentifiersItem(TransactionIdentifier transactionIdentifiersItem) {
    this.transactionIdentifiers.add(transactionIdentifiersItem);
    return this;
  }

  /**
   * Get transactionIdentifiers
   * @return transactionIdentifiers
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<TransactionIdentifier> getTransactionIdentifiers() {
    return transactionIdentifiers;
  }

  public void setTransactionIdentifiers(List<TransactionIdentifier> transactionIdentifiers) {
    this.transactionIdentifiers = transactionIdentifiers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MempoolResponse mempoolResponse = (MempoolResponse) o;
    return Objects.equals(this.transactionIdentifiers, mempoolResponse.transactionIdentifiers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionIdentifiers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MempoolResponse {\n");
    
    sb.append("    transactionIdentifiers: ").append(toIndentedString(transactionIdentifiers)).append("\n");
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

