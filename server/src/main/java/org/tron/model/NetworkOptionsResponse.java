package org.tron.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.tron.model.Allow;
import org.tron.model.Version;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NetworkOptionsResponse contains information about the versioning of the node and the allowed operation statuses, operation types, and errors.
 */
@ApiModel(description = "NetworkOptionsResponse contains information about the versioning of the node and the allowed operation statuses, operation types, and errors.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

public class NetworkOptionsResponse   {
  @JsonProperty("version")
  private Version version;

  @JsonProperty("allow")
  private Allow allow;

  public NetworkOptionsResponse version(Version version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Version getVersion() {
    return version;
  }

  public void setVersion(Version version) {
    this.version = version;
  }

  public NetworkOptionsResponse allow(Allow allow) {
    this.allow = allow;
    return this;
  }

  /**
   * Get allow
   * @return allow
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Allow getAllow() {
    return allow;
  }

  public void setAllow(Allow allow) {
    this.allow = allow;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NetworkOptionsResponse networkOptionsResponse = (NetworkOptionsResponse) o;
    return Objects.equals(this.version, networkOptionsResponse.version) &&
        Objects.equals(this.allow, networkOptionsResponse.allow);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, allow);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NetworkOptionsResponse {\n");
    
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    allow: ").append(toIndentedString(allow)).append("\n");
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

