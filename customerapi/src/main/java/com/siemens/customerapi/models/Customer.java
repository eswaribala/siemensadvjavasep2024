package com.siemens.customerapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name="Customer")
@Inheritance(strategy = InheritanceType.JOINED)
public  class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Account_No")
    @Schema(hidden = true)
    private long accountNo;
    @Embedded
    private FullName fullName;
    @Column(name="Email", nullable = false,length = 150)
    private String email;
    @Column(name="Password", nullable = false,length = 10)
    private String password;
    @Column(name="Contact_No")
    private long contactNo;

}
