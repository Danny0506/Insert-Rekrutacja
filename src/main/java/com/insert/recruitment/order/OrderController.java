package com.insert.recruitment.order;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Void> createOrder(@RequestBody OrderCommand orderCommand) {
    orderService.createOrder(orderCommand);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
    orderService.cancelOrder(orderId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<OrderDto>> getOrders() {
    final List<OrderDto> orders = orderService.getOrders();
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
    final OrderDto order = orderService.getOrder(orderId);
    return new ResponseEntity<>(order, HttpStatus.OK);
  }
}
