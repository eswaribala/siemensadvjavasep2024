package com.siemens.customerapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(hidden = true)
    @Column(name="Address_Id")
    private long addressId;
    @Column(name="Door_No",length = 5,nullable = false)
    private String doorNo;
    @Column(name="Street_Name",length = 150,nullable = false)
    private String streetName;
    @Column(name="City",length = 150,nullable = false)
    private String city;
    @Column(name="Pincode",length = 10,nullable = false)
    private String pincode;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "Account_No"),
            name = "Account_No_FK")
    private Customer customer;
}
