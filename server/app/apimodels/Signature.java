package apimodels;

import apimodels.PublicKey;
import apimodels.SignatureType;
import apimodels.SigningPayload;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * Signature contains the payload that was signed, the public keys of the keypairs used to produce the signature, the signature (encoded in hex), and the SignatureType. PublicKey is often times not known during construction of the signing payloads but may be needed to combine signatures properly.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class Signature   {
  @JsonProperty("signing_payload")
  private SigningPayload signingPayload;

  @JsonProperty("public_key")
  private PublicKey publicKey;

  @JsonProperty("signature_type")
  private SignatureType signatureType;

  @JsonProperty("hex_bytes")
  private String hexBytes;

  public Signature signingPayload(SigningPayload signingPayload) {
    this.signingPayload = signingPayload;
    return this;
  }

   /**
   * Get signingPayload
   * @return signingPayload
  **/
  @NotNull
@Valid
  public SigningPayload getSigningPayload() {
    return signingPayload;
  }

  public void setSigningPayload(SigningPayload signingPayload) {
    this.signingPayload = signingPayload;
  }

  public Signature publicKey(PublicKey publicKey) {
    this.publicKey = publicKey;
    return this;
  }

   /**
   * Get publicKey
   * @return publicKey
  **/
  @NotNull
@Valid
  public PublicKey getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(PublicKey publicKey) {
    this.publicKey = publicKey;
  }

  public Signature signatureType(SignatureType signatureType) {
    this.signatureType = signatureType;
    return this;
  }

   /**
   * Get signatureType
   * @return signatureType
  **/
  @NotNull
@Valid
  public SignatureType getSignatureType() {
    return signatureType;
  }

  public void setSignatureType(SignatureType signatureType) {
    this.signatureType = signatureType;
  }

  public Signature hexBytes(String hexBytes) {
    this.hexBytes = hexBytes;
    return this;
  }

   /**
   * Get hexBytes
   * @return hexBytes
  **/
  @NotNull
  public String getHexBytes() {
    return hexBytes;
  }

  public void setHexBytes(String hexBytes) {
    this.hexBytes = hexBytes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Signature signature = (Signature) o;
    return Objects.equals(signingPayload, signature.signingPayload) &&
        Objects.equals(publicKey, signature.publicKey) &&
        Objects.equals(signatureType, signature.signatureType) &&
        Objects.equals(hexBytes, signature.hexBytes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signingPayload, publicKey, signatureType, hexBytes);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Signature {\n");
    
    sb.append("    signingPayload: ").append(toIndentedString(signingPayload)).append("\n");
    sb.append("    publicKey: ").append(toIndentedString(publicKey)).append("\n");
    sb.append("    signatureType: ").append(toIndentedString(signatureType)).append("\n");
    sb.append("    hexBytes: ").append(toIndentedString(hexBytes)).append("\n");
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

