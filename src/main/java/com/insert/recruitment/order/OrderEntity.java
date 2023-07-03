package com.insert.recruitment.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Order")
class OrderEntity {

  @Id
  @SequenceGenerator(sequenceName = "order_sequence", name = "order_generator", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
  private Long id;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  private String nameOfOrder;

  @NotNull private BigDecimal orderPrice;

  @NotNull private LocalDate dateOfOrder;

  private String description;

  @NotNull @NotBlank private String receiver;

  @NotNull @NotBlank private String sender;
}
