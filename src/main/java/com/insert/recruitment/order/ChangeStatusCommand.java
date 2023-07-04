package com.insert.recruitment.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
class ChangeStatusCommand {
  private Boolean isChangeStatusOrder;
}
