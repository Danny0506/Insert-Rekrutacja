package com.insert.recruitment.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;


record OrderCommand (String nameOfOrder, @NotNull BigDecimal orderPrice, String description, @NotNull @NotBlank String receiver, @NotNull @NotBlank String sender) {
}
