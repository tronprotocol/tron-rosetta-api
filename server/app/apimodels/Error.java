package apimodels;

import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * Instead of utilizing HTTP status codes to describe node errors (which often do not have a good analog), rich errors are returned using this object.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class Error   {
  @JsonProperty("code")
  private Integer code;

  @JsonProperty("message")
  private String message;

  @JsonProperty("retriable")
  private Boolean retriable;

  @JsonProperty("details")
  private Object details;

  public Error code(Integer code) {
    this.code = code;
    return this;
  }

   /**
   * Code is a network-specific error code. If desired, this code can be equivalent to an HTTP status code.
   * minimum: 0
   * @return code
  **/
  @NotNull
@Min(0)
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Error message(String message) {
    this.message = message;
    return this;
  }

   /**
   * Message is a network-specific error message.
   * @return message
  **/
  @NotNull
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Error retriable(Boolean retriable) {
    this.retriable = retriable;
    return this;
  }

   /**
   * An error is retriable if the same request may succeed if submitted again.
   * @return retriable
  **/
  @NotNull
  public Boolean getRetriable() {
    return retriable;
  }

  public void setRetriable(Boolean retriable) {
    this.retriable = retriable;
  }

  public Error details(Object details) {
    this.details = details;
    return this;
  }

   /**
   * Often times it is useful to return context specific to the request that caused the error (i.e. a sample of the stack trace or impacted account) in addition to the standard error message.
   * @return details
  **/
  @Valid
  public Object getDetails() {
    return details;
  }

  public void setDetails(Object details) {
    this.details = details;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(code, error.code) &&
        Objects.equals(message, error.message) &&
        Objects.equals(retriable, error.retriable) &&
        Objects.equals(details, error.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, retriable, details);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    retriable: ").append(toIndentedString(retriable)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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

