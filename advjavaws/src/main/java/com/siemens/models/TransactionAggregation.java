package com.siemens.models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TransactionAggregation {

    private BigDecimal average = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal max = BigDecimal.ZERO;
    private LocalDate lastTransactionDate;
    private List<Transaction> transactionList = new ArrayList<Transaction>();

}
