package apimodels;

import apimodels.NetworkIdentifier;
import apimodels.Operation;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * ConstructionPreprocessRequest is passed to the &#x60;/construction/preprocess&#x60; endpoint so that a Rosetta implementation can determine which metadata it needs to request for construction.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class ConstructionPreprocessRequest   {
  @JsonProperty("network_identifier")
  private NetworkIdentifier networkIdentifier;

  @JsonProperty("operations")
  private List<Operation> operations = new ArrayList<>();

  @JsonProperty("metadata")
  private Object metadata;

  public ConstructionPreprocessRequest networkIdentifier(NetworkIdentifier networkIdentifier) {
    this.networkIdentifier = networkIdentifier;
    return this;
  }

   /**
   * Get networkIdentifier
   * @return networkIdentifier
  **/
  @NotNull
@Valid
  public NetworkIdentifier getNetworkIdentifier() {
    return networkIdentifier;
  }

  public void setNetworkIdentifier(NetworkIdentifier networkIdentifier) {
    this.networkIdentifier = networkIdentifier;
  }

  public ConstructionPreprocessRequest operations(List<Operation> operations) {
    this.operations = operations;
    return this;
  }

  public ConstructionPreprocessRequest addOperationsItem(Operation operationsItem) {
    operations.add(operationsItem);
    return this;
  }

   /**
   * Get operations
   * @return operations
  **/
  @NotNull
@Valid
  public List<Operation> getOperations() {
    return operations;
  }

  public void setOperations(List<Operation> operations) {
    this.operations = operations;
  }

  public ConstructionPreprocessRequest metadata(Object metadata) {
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
    ConstructionPreprocessRequest constructionPreprocessRequest = (ConstructionPreprocessRequest) o;
    return Objects.equals(networkIdentifier, constructionPreprocessRequest.networkIdentifier) &&
        Objects.equals(operations, constructionPreprocessRequest.operations) &&
        Objects.equals(metadata, constructionPreprocessRequest.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(networkIdentifier, operations, metadata);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstructionPreprocessRequest {\n");
    
    sb.append("    networkIdentifier: ").append(toIndentedString(networkIdentifier)).append("\n");
    sb.append("    operations: ").append(toIndentedString(operations)).append("\n");
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

