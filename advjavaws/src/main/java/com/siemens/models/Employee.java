package com.siemens.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public sealed class Employee  permits  FullTimeEmployee,ContractEmployee{
    private int basicSalary;
    private double bonus;
}
