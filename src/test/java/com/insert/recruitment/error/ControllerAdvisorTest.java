package com.insert.recruitment.error;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

import com.insert.recruitment.exception.CannotChangeOrderStatusException;
import com.insert.recruitment.exception.OrderNotExistException;
import jakarta.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ControllerAdvisorTest {

  private static final String SOURCE = "INSERT_RECRUITMENT_API";

  private final ControllerAdvisor controllerAdvisor = new ControllerAdvisor();

  @Test
  void shouldHandleCannotChangeOrderStatusException() {
    // given
    final String status = "ACTIVATED";
    final CannotChangeOrderStatusException exception = new CannotChangeOrderStatusException(status);
    final String expectedResponse = "Order already has ACTIVATED status.";

    // when
    ResponseEntity<ErrorMessage> response =
        controllerAdvisor.handleCannotChangeOrderStatusException(exception);

    // then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(requireNonNull(response.getBody()).message()).isEqualTo(expectedResponse);
    assertThat(requireNonNull(response.getBody()).source()).isEqualTo(SOURCE);
  }

  @Test
  void shouldHandleConstraintViolationException() {
    // given
    final String message = "message";
    final ConstraintViolationException exception =
        new ConstraintViolationException(message, Set.of());

    // when
    ResponseEntity<ErrorMessage> response =
        controllerAdvisor.handleConstraintViolationException(exception);

    // then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(requireNonNull(response.getBody()).message()).isEqualTo(message);
    assertThat(requireNonNull(response.getBody()).source()).isEqualTo(SOURCE);
  }

  @Test
  void shouldHandleSQLIntegrityConstraintViolationException() {
    // given
    final String reason = "ERROR";
    final SQLIntegrityConstraintViolationException exception =
        new SQLIntegrityConstraintViolationException(reason);

    // when
    ResponseEntity<ErrorMessage> response =
        controllerAdvisor.handleSQLIntegrityConstraintViolationException(exception);

    // then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(requireNonNull(response.getBody()).message()).isEqualTo(reason);
    assertThat(requireNonNull(response.getBody()).source()).isEqualTo(SOURCE);
  }

  @Test
  void shouldHandleOrderNotExistException() {
    // given
    final Long id = 1L;
    final OrderNotExistException exception = new OrderNotExistException(id);
    final String expectedResponse = "Order with id 1 does not exists.";

    // when
    ResponseEntity<ErrorMessage> response =
        controllerAdvisor.handleOrderNotExistException(exception);

    // then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(requireNonNull(response.getBody()).message()).isEqualTo(expectedResponse);
    assertThat(requireNonNull(response.getBody()).source()).isEqualTo(SOURCE);
  }
}
