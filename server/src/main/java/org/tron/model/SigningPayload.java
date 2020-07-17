package org.tron.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.tron.model.SignatureType;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SigningPayload is signed by the client with the keypair associated with an address using the specified SignatureType. SignatureType can be optionally populated if there is a restriction on the signature scheme that can be used to sign the payload.
 */
@ApiModel(description = "SigningPayload is signed by the client with the keypair associated with an address using the specified SignatureType. SignatureType can be optionally populated if there is a restriction on the signature scheme that can be used to sign the payload.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

public class SigningPayload   {
  @JsonProperty("address")
  private String address;

  @JsonProperty("hex_bytes")
  private String hexBytes;

  @JsonProperty("signature_type")
  private SignatureType signatureType;

  public SigningPayload address(String address) {
    this.address = address;
    return this;
  }

  /**
   * The network-specific address of the account that should sign the payload.
   * @return address
  */
  @ApiModelProperty(required = true, value = "The network-specific address of the account that should sign the payload.")
  @NotNull


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public SigningPayload hexBytes(String hexBytes) {
    this.hexBytes = hexBytes;
    return this;
  }

  /**
   * Get hexBytes
   * @return hexBytes
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getHexBytes() {
    return hexBytes;
  }

  public void setHexBytes(String hexBytes) {
    this.hexBytes = hexBytes;
  }

  public SigningPayload signatureType(SignatureType signatureType) {
    this.signatureType = signatureType;
    return this;
  }

  /**
   * Get signatureType
   * @return signatureType
  */
  @ApiModelProperty(value = "")

  @Valid

  public SignatureType getSignatureType() {
    return signatureType;
  }

  public void setSignatureType(SignatureType signatureType) {
    this.signatureType = signatureType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SigningPayload signingPayload = (SigningPayload) o;
    return Objects.equals(this.address, signingPayload.address) &&
        Objects.equals(this.hexBytes, signingPayload.hexBytes) &&
        Objects.equals(this.signatureType, signingPayload.signatureType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, hexBytes, signatureType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SigningPayload {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    hexBytes: ").append(toIndentedString(hexBytes)).append("\n");
    sb.append("    signatureType: ").append(toIndentedString(signatureType)).append("\n");
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

