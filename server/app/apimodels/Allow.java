package apimodels;

import apimodels.Error;
import apimodels.OperationStatus;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * Allow specifies supported Operation status, Operation types, and all possible error statuses. This Allow object is used by clients to validate the correctness of a Rosetta Server implementation. It is expected that these clients will error if they receive some response that contains any of the above information that is not specified here.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaPlayFrameworkCodegen", date = "2020-07-01T16:39:23.248338+08:00[Asia/Shanghai]")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class Allow   {
  @JsonProperty("operation_statuses")
  private List<OperationStatus> operationStatuses = new ArrayList<>();

  @JsonProperty("operation_types")
  private List<String> operationTypes = new ArrayList<>();

  @JsonProperty("errors")
  private List<Error> errors = new ArrayList<>();

  @JsonProperty("historical_balance_lookup")
  private Boolean historicalBalanceLookup;

  public Allow operationStatuses(List<OperationStatus> operationStatuses) {
    this.operationStatuses = operationStatuses;
    return this;
  }

  public Allow addOperationStatusesItem(OperationStatus operationStatusesItem) {
    operationStatuses.add(operationStatusesItem);
    return this;
  }

   /**
   * All Operation.Status this implementation supports. Any status that is returned during parsing that is not listed here will cause client validation to error.
   * @return operationStatuses
  **/
  @NotNull
@Valid
  public List<OperationStatus> getOperationStatuses() {
    return operationStatuses;
  }

  public void setOperationStatuses(List<OperationStatus> operationStatuses) {
    this.operationStatuses = operationStatuses;
  }

  public Allow operationTypes(List<String> operationTypes) {
    this.operationTypes = operationTypes;
    return this;
  }

  public Allow addOperationTypesItem(String operationTypesItem) {
    operationTypes.add(operationTypesItem);
    return this;
  }

   /**
   * All Operation.Type this implementation supports. Any type that is returned during parsing that is not listed here will cause client validation to error.
   * @return operationTypes
  **/
  @NotNull
  public List<String> getOperationTypes() {
    return operationTypes;
  }

  public void setOperationTypes(List<String> operationTypes) {
    this.operationTypes = operationTypes;
  }

  public Allow errors(List<Error> errors) {
    this.errors = errors;
    return this;
  }

  public Allow addErrorsItem(Error errorsItem) {
    errors.add(errorsItem);
    return this;
  }

   /**
   * All Errors that this implementation could return. Any error that is returned during parsing that is not listed here will cause client validation to error.
   * @return errors
  **/
  @NotNull
@Valid
  public List<Error> getErrors() {
    return errors;
  }

  public void setErrors(List<Error> errors) {
    this.errors = errors;
  }

  public Allow historicalBalanceLookup(Boolean historicalBalanceLookup) {
    this.historicalBalanceLookup = historicalBalanceLookup;
    return this;
  }

   /**
   * Any Rosetta implementation that supports querying the balance of an account at any height in the past should set this to true.
   * @return historicalBalanceLookup
  **/
  @NotNull
  public Boolean getHistoricalBalanceLookup() {
    return historicalBalanceLookup;
  }

  public void setHistoricalBalanceLookup(Boolean historicalBalanceLookup) {
    this.historicalBalanceLookup = historicalBalanceLookup;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Allow allow = (Allow) o;
    return Objects.equals(operationStatuses, allow.operationStatuses) &&
        Objects.equals(operationTypes, allow.operationTypes) &&
        Objects.equals(errors, allow.errors) &&
        Objects.equals(historicalBalanceLookup, allow.historicalBalanceLookup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationStatuses, operationTypes, errors, historicalBalanceLookup);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Allow {\n");
    
    sb.append("    operationStatuses: ").append(toIndentedString(operationStatuses)).append("\n");
    sb.append("    operationTypes: ").append(toIndentedString(operationTypes)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("    historicalBalanceLookup: ").append(toIndentedString(historicalBalanceLookup)).append("\n");
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

