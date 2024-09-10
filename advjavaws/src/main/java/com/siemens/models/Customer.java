package com.siemens.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data //Getter,Setter, ToString Equals and HashCode
@AllArgsConstructor // Parameterized constructor
@NoArgsConstructor // default constructor
public class Customer {
    private String name;
    private LocalDate dob;
}
