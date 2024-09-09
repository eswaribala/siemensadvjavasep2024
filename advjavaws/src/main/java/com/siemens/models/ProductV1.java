package com.siemens.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductV1 {
    private long productId;
    private String name;
    private int qty;
    private String unit;
    private double cost;
    private Location location;

}
