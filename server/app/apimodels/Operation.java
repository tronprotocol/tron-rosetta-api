package apimodels;

import apimodels.AccountIdentifier;
import apimodels.Amount;
import apimodels.OperationIdentifier;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * Operations contain all balance-changing information within a transaction. They are always one-sided (only affect 1 AccountIdentifier) and can succeed or fail independently from a Transaction.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class Operation   {
  @JsonProperty("operation_identifier")
  private OperationIdentifier operationIdentifier;

  @JsonProperty("related_operations")
  private List<OperationIdentifier> relatedOperations = null;

  @JsonProperty("type")
  private String type;

  @JsonProperty("status")
  private String status;

  @JsonProperty("account")
  private AccountIdentifier account;

  @JsonProperty("amount")
  private Amount amount;

  @JsonProperty("metadata")
  private Object metadata;

  public Operation operationIdentifier(OperationIdentifier operationIdentifier) {
    this.operationIdentifier = operationIdentifier;
    return this;
  }

   /**
   * Get operationIdentifier
   * @return operationIdentifier
  **/
  @NotNull
@Valid
  public OperationIdentifier getOperationIdentifier() {
    return operationIdentifier;
  }

  public void setOperationIdentifier(OperationIdentifier operationIdentifier) {
    this.operationIdentifier = operationIdentifier;
  }

  public Operation relatedOperations(List<OperationIdentifier> relatedOperations) {
    this.relatedOperations = relatedOperations;
    return this;
  }

  public Operation addRelatedOperationsItem(OperationIdentifier relatedOperationsItem) {
    if (relatedOperations == null) {
      relatedOperations = new ArrayList<>();
    }
    relatedOperations.add(relatedOperationsItem);
    return this;
  }

   /**
   * Restrict referenced related_operations to identifier indexes < the current operation_identifier.index. This ensures there exists a clear DAG-structure of relations. Since operations are one-sided, one could imagine relating operations in a single transfer or linking operations in a call tree.
   * @return relatedOperations
  **/
  @Valid
  public List<OperationIdentifier> getRelatedOperations() {
    return relatedOperations;
  }

  public void setRelatedOperations(List<OperationIdentifier> relatedOperations) {
    this.relatedOperations = relatedOperations;
  }

  public Operation type(String type) {
    this.type = type;
    return this;
  }

   /**
   * The network-specific type of the operation. Ensure that any type that can be returned here is also specified in the NetworkStatus. This can be very useful to downstream consumers that parse all block data.
   * @return type
  **/
  @NotNull
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Operation status(String status) {
    this.status = status;
    return this;
  }

   /**
   * The network-specific status of the operation. Status is not defined on the transaction object because blockchains with smart contracts may have transactions that partially apply. Blockchains with atomic transactions (all operations succeed or all operations fail) will have the same status for each operation.
   * @return status
  **/
  @NotNull
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Operation account(AccountIdentifier account) {
    this.account = account;
    return this;
  }

   /**
   * Get account
   * @return account
  **/
  @Valid
  public AccountIdentifier getAccount() {
    return account;
  }

  public void setAccount(AccountIdentifier account) {
    this.account = account;
  }

  public Operation amount(Amount amount) {
    this.amount = amount;
    return this;
  }

   /**
   * Get amount
   * @return amount
  **/
  @Valid
  public Amount getAmount() {
    return amount;
  }

  public void setAmount(Amount amount) {
    this.amount = amount;
  }

  public Operation metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

   /**
   * Get metadata
   * @return metadata
  **/
  @Valid
  public Object getMetadata() {
    return metadata;
  }

  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Operation operation = (Operation) o;
    return Objects.equals(operationIdentifier, operation.operationIdentifier) &&
        Objects.equals(relatedOperations, operation.relatedOperations) &&
        Objects.equals(type, operation.type) &&
        Objects.equals(status, operation.status) &&
        Objects.equals(account, operation.account) &&
        Objects.equals(amount, operation.amount) &&
        Objects.equals(metadata, operation.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationIdentifier, relatedOperations, type, status, account, amount, metadata);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Operation {\n");
    
    sb.append("    operationIdentifier: ").append(toIndentedString(operationIdentifier)).append("\n");
    sb.append("    relatedOperations: ").append(toIndentedString(relatedOperations)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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

