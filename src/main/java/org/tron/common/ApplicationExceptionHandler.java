package org.tron.common;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tron.config.Constant;
import org.tron.model.Error;

import java.util.List;

@RestControllerAdvice("org.tron.api")
public class ApplicationExceptionHandler {
  /**
   * @param e
   * @return
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected Error handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
    BindingResult exceptions = e.getBindingResult();
    if (exceptions.hasErrors()) {
      List<ObjectError> errors = exceptions.getAllErrors();
      if (!errors.isEmpty()) {
        FieldError fieldError = (FieldError) errors.get(0);
        return Constant.newError(Constant.INVALID_REQUEST_FORMAT)
            .retriable(false)
            .details(fieldError.getField());
      }
    }
    return Constant.INVALID_REQUEST_FORMAT;
  }

  @ExceptionHandler({BindException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected Error handleBindException(BindException exceptions) {
    if (exceptions.hasErrors()) {
      List<ObjectError> errors = exceptions.getAllErrors();
      if (!errors.isEmpty()) {
        FieldError fieldError = (FieldError) errors.get(0);
        return Constant.newError(Constant.INVALID_REQUEST_FORMAT)
            .details(fieldError.getDefaultMessage());
      }
    }
    return Constant.INVALID_REQUEST_FORMAT;
  }

  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected Error handleException(Exception exception) {
    return Constant.newError(Constant.INTERNAL_SERVER_ERROR)
        .details(exception.getMessage());
  }
}
