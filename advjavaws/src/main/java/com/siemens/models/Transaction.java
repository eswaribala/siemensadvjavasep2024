package com.siemens.models;


import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class Transaction {

    private Long id;
    private Long userId;
    private BigDecimal amount = BigDecimal.ZERO;
    private LocalDate dateTime;

}