package com.prokarma.pkmst.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.prokarma.pkmst.model.NetworkIdentifier;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
/**
 * Response class to be returned by Api
 * @author pkmst
 *
 */
/**
 * A NetworkListResponse contains all NetworkIdentifiers that the node can serve information for.
 */
@ApiModel(description = "A NetworkListResponse contains all NetworkIdentifiers that the node can serve information for.")

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPKMSTServerCodegen", date = "2020-07-01T16:38:10.259179+08:00[Asia/Shanghai]")

public class NetworkListResponse   {
  @JsonProperty("network_identifiers")
  
  private List<NetworkIdentifier> networkIdentifiers = new ArrayList<>();

  public NetworkListResponse networkIdentifiers(List<NetworkIdentifier> networkIdentifiers) {
    this.networkIdentifiers = networkIdentifiers;
    return this;
  }

  public NetworkListResponse addNetworkIdentifiersItem(NetworkIdentifier networkIdentifiersItem) {
    this.networkIdentifiers.add(networkIdentifiersItem);
    return this;
  }

   /**
   * Get networkIdentifiers
   * @return networkIdentifiers
  **/
  @ApiModelProperty(required = true, value = "")
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
    return Objects.equals(this.networkIdentifiers, networkListResponse.networkIdentifiers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(networkIdentifiers);
  }

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

