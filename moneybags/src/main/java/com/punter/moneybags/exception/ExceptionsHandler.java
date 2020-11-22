package com.punter.moneybags.exception;

import java.util.HashMap;
import java.util.Map;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

/**
 * TODO: ErrorDetail, custom body for errors
 */
@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleMethodArgumentNotValidExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    logErrorToDebug(errors);
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public Map<String, String> handleMethodArgumentTypeMismatchExceptions(
      MethodArgumentTypeMismatchException ex) {

    Map<String, String> errors = new HashMap<>();
    errors.put(ex.getName(),
        ex.getName() + "'s type must be " + ex.getRequiredType().getSimpleName());

    logErrorToDebug(errors);
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ConstraintViolationException.class})
  public Map<String, String> handleConstraintViolationExceptions(ConstraintViolationException ex) {

    Map<String, String> errors = new HashMap<>();
    ex.getConstraintViolations().forEach(error -> {
      errors.put(error.getPropertyPath().toString(), error.getMessage());
    });

    logErrorToDebug(errors);
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({MultipartException.class})
  public Map<String, String> handleMultipartExceptions(MultipartException ex) {

    Map<String, String> errors = new HashMap<>();
    errors.put("Advice", "Please post a valid CSV file");
    errors.put("Message", ex.getMessage());

    logErrorToDebug(errors);
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({Exception.class})
  public Map<String, String> handleExceptions(Exception ex) {

    Map<String, String> errors = new HashMap<>();
    errors.put("Hey", "I'll handle this later");
    errors.put("Cause", ex.getCause().getMessage());

    logErrorToFlagUnhandledExceptionAsIssue(errors);
    return errors;
  }

  private void logErrorToDebug(Map<String, String> errorsMap) {
    log.debug(String.valueOf(errorsMap));
  }

  private void logErrorToFlagUnhandledExceptionAsIssue(Map<String, String> errorsMap) {
    log.error(String.valueOf(errorsMap));
  }

}
