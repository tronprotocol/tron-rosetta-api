package com.prokarma.pkmst.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.prokarma.pkmst.model.CurveType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * Response class to be returned by Api
 * @author pkmst
 *
 */
/**
 * PublicKey contains a public key byte array for a particular CurveType encoded in hex. Note that there is no PrivateKey struct as this is NEVER the concern of an implementation.
 */
@ApiModel(description = "PublicKey contains a public key byte array for a particular CurveType encoded in hex. Note that there is no PrivateKey struct as this is NEVER the concern of an implementation.")

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPKMSTServerCodegen", date = "2020-07-01T16:38:10.259179+08:00[Asia/Shanghai]")

public class PublicKey   {
  @JsonProperty("hex_bytes")
  private String hexBytes;

  @JsonProperty("curve_type")
  private CurveType curveType;

  public PublicKey hexBytes(String hexBytes) {
    this.hexBytes = hexBytes;
    return this;
  }

   /**
   * Hex-encoded public key bytes in the format specified by the CurveType.
   * @return hexBytes
  **/
  @ApiModelProperty(required = true, value = "Hex-encoded public key bytes in the format specified by the CurveType.")
  public String getHexBytes() {
    return hexBytes;
  }

  public void setHexBytes(String hexBytes) {
    this.hexBytes = hexBytes;
  }

  public PublicKey curveType(CurveType curveType) {
    this.curveType = curveType;
    return this;
  }

   /**
   * Get curveType
   * @return curveType
  **/
  @ApiModelProperty(required = true, value = "")
  public CurveType getCurveType() {
    return curveType;
  }

  public void setCurveType(CurveType curveType) {
    this.curveType = curveType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PublicKey publicKey = (PublicKey) o;
    return Objects.equals(this.hexBytes, publicKey.hexBytes) &&
        Objects.equals(this.curveType, publicKey.curveType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hexBytes, curveType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PublicKey {\n");
    
    sb.append("    hexBytes: ").append(toIndentedString(hexBytes)).append("\n");
    sb.append("    curveType: ").append(toIndentedString(curveType)).append("\n");
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

