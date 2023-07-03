package com.insert.recruitment.order;

import java.math.BigDecimal;

record OrderCommand (String nameOfOrder, BigDecimal orderPrice, String description, String receiver, String sender) {
}
