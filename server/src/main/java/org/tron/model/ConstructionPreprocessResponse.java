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
 * ConstructionPreprocessResponse contains the request that will be sent directly to &#x60;/construction/metadata&#x60;. If it is not necessary to make a request to &#x60;/construction/metadata&#x60;, options should be null.
 */
@ApiModel(description = "ConstructionPreprocessResponse contains the request that will be sent directly to `/construction/metadata`. If it is not necessary to make a request to `/construction/metadata`, options should be null.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

public class ConstructionPreprocessResponse   {
  @JsonProperty("options")
  private Object options;

  public ConstructionPreprocessResponse options(Object options) {
    this.options = options;
    return this;
  }

  /**
   * The options that will be sent directly to `/construction/metadata` by the caller.
   * @return options
  */
  @ApiModelProperty(value = "The options that will be sent directly to `/construction/metadata` by the caller.")

  @Valid

  public Object getOptions() {
    return options;
  }

  public void setOptions(Object options) {
    this.options = options;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConstructionPreprocessResponse constructionPreprocessResponse = (ConstructionPreprocessResponse) o;
    return Objects.equals(this.options, constructionPreprocessResponse.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(options);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstructionPreprocessResponse {\n");
    
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
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

