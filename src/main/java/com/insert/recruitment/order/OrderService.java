package com.insert.recruitment.order;

import static java.lang.String.format;

import com.insert.recruitment.exception.CannotChangeOrderStatusException;
import com.insert.recruitment.exception.OrderNotExistException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderService {

  private final OrderRepository orderRepository;

  public void createOrder(OrderCommand orderCommand) {
    final OrderEntity order = new OrderEntity();
    order.setDescription(orderCommand.getDescription());
    order.setDateOfOrder(LocalDate.now());
    order.setNameOfOrder(orderCommand.getNameOfOrder());
    order.setStatus(OrderStatus.CREATED);
    order.setSender(orderCommand.getSender());
    order.setReceiver(orderCommand.getReceiver());
    order.setOrderPrice(orderCommand.getOrderPrice());
    orderRepository.save(order);
    log.info(format("Order about id: %s was created in application", order.getId()));
  }

  public void cancelOrder(Long orderId) {
    final OrderEntity order = resolveOrderEntityFromDatabase(orderId);
    orderRepository.delete(order);
    log.info(format("Order about id: %s was deleted from application", orderId));
  }

  public OrderDto acceptOrder(Long orderId) {
    final OrderEntity order = resolveOrderEntityFromDatabase(orderId);
    if (OrderStatus.APPROVED.equals(order.getStatus())) {
      throw new CannotChangeOrderStatusException(order.getStatus().name());
    } else {
      order.setStatus(OrderStatus.APPROVED);
      return toDto(order);
    }
  }

  public OrderDto endOrder(Long orderId) {
    final OrderEntity order = resolveOrderEntityFromDatabase(orderId);
    if (OrderStatus.COMPLETED.equals(order.getStatus())) {
      throw new CannotChangeOrderStatusException(order.getStatus().name());
    } else {
      order.setStatus(OrderStatus.COMPLETED);
      return toDto(order);
    }
  }

  public List<OrderDto> getOrders() {
    return orderRepository.findAll().stream().map(this::toDto).toList();
  }

  public OrderDto getOrder(Long orderId) {
    return this.toDto(resolveOrderEntityFromDatabase(orderId));
  }

  private OrderEntity resolveOrderEntityFromDatabase(Long orderId) {
    return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotExistException(orderId));
  }

  private OrderDto toDto(OrderEntity orderEntity) {
    return new OrderDto(
        orderEntity.getId(),
        orderEntity.getStatus(),
        orderEntity.getNameOfOrder(),
        orderEntity.getOrderPrice(),
        orderEntity.getDateOfOrder(),
        orderEntity.getDescription(),
        orderEntity.getReceiver(),
        orderEntity.getSender());
  }
}
