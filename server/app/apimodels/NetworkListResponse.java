package apimodels;

import apimodels.NetworkIdentifier;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * A NetworkListResponse contains all NetworkIdentifiers that the node can serve information for.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class NetworkListResponse   {
  @JsonProperty("network_identifiers")
  private List<NetworkIdentifier> networkIdentifiers = new ArrayList<>();

  public NetworkListResponse networkIdentifiers(List<NetworkIdentifier> networkIdentifiers) {
    this.networkIdentifiers = networkIdentifiers;
    return this;
  }

  public NetworkListResponse addNetworkIdentifiersItem(NetworkIdentifier networkIdentifiersItem) {
    networkIdentifiers.add(networkIdentifiersItem);
    return this;
  }

   /**
   * Get networkIdentifiers
   * @return networkIdentifiers
  **/
  @NotNull
@Valid
  public List<NetworkIdentifier> getNetworkIdentifiers() {
    return networkIdentifiers;
  }

  public void setNetworkIdentifiers(List<NetworkIdentifier> networkIdentifiers) {
    this.networkIdentifiers = networkIdentifiers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NetworkListResponse networkListResponse = (NetworkListResponse) o;
    return Objects.equals(networkIdentifiers, networkListResponse.networkIdentifiers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(networkIdentifiers);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NetworkListResponse {\n");
    
    sb.append("    networkIdentifiers: ").append(toIndentedString(networkIdentifiers)).append("\n");
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

