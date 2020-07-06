package apimodels;

import apimodels.TransactionIdentifier;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * A MempoolResponse contains all transaction identifiers in the mempool for a particular network_identifier.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class MempoolResponse   {
  @JsonProperty("transaction_identifiers")
  private List<TransactionIdentifier> transactionIdentifiers = new ArrayList<>();

  public MempoolResponse transactionIdentifiers(List<TransactionIdentifier> transactionIdentifiers) {
    this.transactionIdentifiers = transactionIdentifiers;
    return this;
  }

  public MempoolResponse addTransactionIdentifiersItem(TransactionIdentifier transactionIdentifiersItem) {
    transactionIdentifiers.add(transactionIdentifiersItem);
    return this;
  }

   /**
   * Get transactionIdentifiers
   * @return transactionIdentifiers
  **/
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
    return Objects.equals(transactionIdentifiers, mempoolResponse.transactionIdentifiers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionIdentifiers);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
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

