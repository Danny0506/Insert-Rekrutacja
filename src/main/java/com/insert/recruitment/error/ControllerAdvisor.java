package com.insert.recruitment.error;

import static com.insert.recruitment.error.ReasonCode.ORDER_NOT_EXIST;
import static java.lang.String.format;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import com.insert.recruitment.exception.OrderNotExistException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
@Slf4j
public class ControllerAdvisor {

  private static final String SOURCE = "INSERT_RECRUITMENT_API";

  @ExceptionHandler(OrderNotExistException.class)
  public ResponseEntity<ErrorMessage> handleOrderNotExistException(
      OrderNotExistException exception) {
    log.info(format("Error was occurred. Message: %s ", exception.getMessage()));
    log.debug(format("Error was occurred. Message: %s ", exception.getMessage()));

    final ErrorMessage errorMessage =
        new ErrorMessage(
            SOURCE, LocalDateTime.now(), ORDER_NOT_EXIST.name(), exception.getMessage());

    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
  }
}
