package com.siemens.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductV2 {
    private long productId;
    private String name;
    private int qty;
    private String unit;
    private BigDecimal cost = BigDecimal.ZERO;

}
