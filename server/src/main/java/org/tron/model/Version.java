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
 * The Version object is utilized to inform the client of the versions of different components of the Rosetta implementation.
 */
@ApiModel(description = "The Version object is utilized to inform the client of the versions of different components of the Rosetta implementation.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

public class Version   {
  @JsonProperty("rosetta_version")
  private String rosettaVersion;

  @JsonProperty("node_version")
  private String nodeVersion;

  @JsonProperty("middleware_version")
  private String middlewareVersion;

  @JsonProperty("metadata")
  private Object metadata;

  public Version rosettaVersion(String rosettaVersion) {
    this.rosettaVersion = rosettaVersion;
    return this;
  }

  /**
   * The rosetta_version is the version of the Rosetta interface the implementation adheres to. This can be useful for clients looking to reliably parse responses.
   * @return rosettaVersion
  */
  @ApiModelProperty(example = "1.2.5", required = true, value = "The rosetta_version is the version of the Rosetta interface the implementation adheres to. This can be useful for clients looking to reliably parse responses.")
  @NotNull


  public String getRosettaVersion() {
    return rosettaVersion;
  }

  public void setRosettaVersion(String rosettaVersion) {
    this.rosettaVersion = rosettaVersion;
  }

  public Version nodeVersion(String nodeVersion) {
    this.nodeVersion = nodeVersion;
    return this;
  }

  /**
   * The node_version is the canonical version of the node runtime. This can help clients manage deployments.
   * @return nodeVersion
  */
  @ApiModelProperty(example = "1.0.2", required = true, value = "The node_version is the canonical version of the node runtime. This can help clients manage deployments.")
  @NotNull


  public String getNodeVersion() {
    return nodeVersion;
  }

  public void setNodeVersion(String nodeVersion) {
    this.nodeVersion = nodeVersion;
  }

  public Version middlewareVersion(String middlewareVersion) {
    this.middlewareVersion = middlewareVersion;
    return this;
  }

  /**
   * When a middleware server is used to adhere to the Rosetta interface, it should return its version here. This can help clients manage deployments.
   * @return middlewareVersion
  */
  @ApiModelProperty(example = "0.2.7", value = "When a middleware server is used to adhere to the Rosetta interface, it should return its version here. This can help clients manage deployments.")


  public String getMiddlewareVersion() {
    return middlewareVersion;
  }

  public void setMiddlewareVersion(String middlewareVersion) {
    this.middlewareVersion = middlewareVersion;
  }

  public Version metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Any other information that may be useful about versioning of dependent services should be returned here.
   * @return metadata
  */
  @ApiModelProperty(value = "Any other information that may be useful about versioning of dependent services should be returned here.")

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
    Version version = (Version) o;
    return Objects.equals(this.rosettaVersion, version.rosettaVersion) &&
        Objects.equals(this.nodeVersion, version.nodeVersion) &&
        Objects.equals(this.middlewareVersion, version.middlewareVersion) &&
        Objects.equals(this.metadata, version.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rosettaVersion, nodeVersion, middlewareVersion, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Version {\n");
    
    sb.append("    rosettaVersion: ").append(toIndentedString(rosettaVersion)).append("\n");
    sb.append("    nodeVersion: ").append(toIndentedString(nodeVersion)).append("\n");
    sb.append("    middlewareVersion: ").append(toIndentedString(middlewareVersion)).append("\n");
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

