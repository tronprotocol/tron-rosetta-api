package apimodels;

import apimodels.CurveType;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * PublicKey contains a public key byte array for a particular CurveType encoded in hex. Note that there is no PrivateKey struct as this is NEVER the concern of an implementation.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
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
  @NotNull
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
  @NotNull
@Valid
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
    return Objects.equals(hexBytes, publicKey.hexBytes) &&
        Objects.equals(curveType, publicKey.curveType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hexBytes, curveType);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
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

