package de.volkswagen.f73.backend.exceptionsHandlerApi;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class CustomApiException {

  private final String message;
  private final Throwable cause;
  private final HttpStatus status;
  private final ZonedDateTime timeStamp;

  public CustomApiException(String message, Throwable cause, HttpStatus status, ZonedDateTime timeStamp) {
    this.message = message;
    this.cause = cause;
    this.status = status;
    this.timeStamp = timeStamp;
  }

  public String getMessage() {
    return message;
  }

  public Throwable getCause() {
    return cause;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public ZonedDateTime getTimeStamp() {
    return timeStamp;
  }
}
