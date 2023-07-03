package com.insert.recruitment.order;

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
public class OrderService {

  private final OrderRepository orderRepository;

  @Transactional
  public void createOrder(OrderCommand orderCommand) {
    final OrderEntity order = new OrderEntity();
    order.setDescription(orderCommand.description());
    order.setDateOfOrder(LocalDate.now());
    order.setNameOfOrder(orderCommand.nameOfOrder());
    order.setStatus(OrderStatus.CREATED);
    order.setSender(orderCommand.sender());
    order.setReceiver(orderCommand.receiver());
    order.setOrderPrice(orderCommand.orderPrice());
    orderRepository.save(order);
  }

  @Transactional
  public void cancelOrder(Long orderId) {
    final OrderEntity order = resolveOrderEntityFromDatabase(orderId);
    orderRepository.delete(order);
  }

  @Transactional(readOnly = true)
  public List<OrderDto> getOrders() {
    return orderRepository.findAll().stream().map(this::toDto).toList();
  }

  @Transactional(readOnly = true)
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
