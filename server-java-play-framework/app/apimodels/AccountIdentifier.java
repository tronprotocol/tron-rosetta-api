package apimodels;

import apimodels.SubAccountIdentifier;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * The account_identifier uniquely identifies an account within a network. All fields in the account_identifier are utilized to determine this uniqueness (including the metadata field, if populated).
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class AccountIdentifier   {
  @JsonProperty("address")
  private String address;

  @JsonProperty("sub_account")
  private SubAccountIdentifier subAccount;

  @JsonProperty("metadata")
  private Object metadata;

  public AccountIdentifier address(String address) {
    this.address = address;
    return this;
  }

   /**
   * The address may be a cryptographic public key (or some encoding of it) or a provided username.
   * @return address
  **/
  @NotNull
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public AccountIdentifier subAccount(SubAccountIdentifier subAccount) {
    this.subAccount = subAccount;
    return this;
  }

   /**
   * Get subAccount
   * @return subAccount
  **/
  @Valid
  public SubAccountIdentifier getSubAccount() {
    return subAccount;
  }

  public void setSubAccount(SubAccountIdentifier subAccount) {
    this.subAccount = subAccount;
  }

  public AccountIdentifier metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

   /**
   * Blockchains that utilize a username model (where the address is not a derivative of a cryptographic public key) should specify the public key(s) owned by the address in metadata.
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
    AccountIdentifier accountIdentifier = (AccountIdentifier) o;
    return Objects.equals(address, accountIdentifier.address) &&
        Objects.equals(subAccount, accountIdentifier.subAccount) &&
        Objects.equals(metadata, accountIdentifier.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, subAccount, metadata);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountIdentifier {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    subAccount: ").append(toIndentedString(subAccount)).append("\n");
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

