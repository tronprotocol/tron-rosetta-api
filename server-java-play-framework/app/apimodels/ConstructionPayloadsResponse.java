package apimodels;

import apimodels.SigningPayload;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * ConstructionTransactionResponse is returned by &#x60;/construction/payloads&#x60;. It contains an unsigned transaction blob (that is usually needed to construct the a network transaction from a collection of signatures) and an array of payloads that must be signed by the caller.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class ConstructionPayloadsResponse   {
  @JsonProperty("unsigned_transaction")
  private String unsignedTransaction;

  @JsonProperty("payloads")
  private List<SigningPayload> payloads = new ArrayList<>();

  public ConstructionPayloadsResponse unsignedTransaction(String unsignedTransaction) {
    this.unsignedTransaction = unsignedTransaction;
    return this;
  }

   /**
   * Get unsignedTransaction
   * @return unsignedTransaction
  **/
  @NotNull
  public String getUnsignedTransaction() {
    return unsignedTransaction;
  }

  public void setUnsignedTransaction(String unsignedTransaction) {
    this.unsignedTransaction = unsignedTransaction;
  }

  public ConstructionPayloadsResponse payloads(List<SigningPayload> payloads) {
    this.payloads = payloads;
    return this;
  }

  public ConstructionPayloadsResponse addPayloadsItem(SigningPayload payloadsItem) {
    payloads.add(payloadsItem);
    return this;
  }

   /**
   * Get payloads
   * @return payloads
  **/
  @NotNull
@Valid
  public List<SigningPayload> getPayloads() {
    return payloads;
  }

  public void setPayloads(List<SigningPayload> payloads) {
    this.payloads = payloads;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConstructionPayloadsResponse constructionPayloadsResponse = (ConstructionPayloadsResponse) o;
    return Objects.equals(unsignedTransaction, constructionPayloadsResponse.unsignedTransaction) &&
        Objects.equals(payloads, constructionPayloadsResponse.payloads);
  }

  @Override
  public int hashCode() {
    return Objects.hash(unsignedTransaction, payloads);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstructionPayloadsResponse {\n");
    
    sb.append("    unsignedTransaction: ").append(toIndentedString(unsignedTransaction)).append("\n");
    sb.append("    payloads: ").append(toIndentedString(payloads)).append("\n");
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

