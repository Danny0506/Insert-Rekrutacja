package com.insert.recruitment.order;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCommand {
  private String nameOfOrder;
  private BigDecimal orderPrice;
  private String description;
  private String receiver;
  private String sender;
}
