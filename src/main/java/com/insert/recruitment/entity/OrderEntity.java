package com.insert.recruitment.entity;

import com.insert.recruitment.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Table(name = "Order")
public class OrderEntity {

  @Id
  @SequenceGenerator(sequenceName = "order_sequence", name = "order_generator", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
  private Long id;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  private String nameOfOrder;

  private BigDecimal orderPrice;

  private LocalDate dateOfOrder;
}
