package com.siemens.customerapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Corporate")
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Corporate extends Customer implements Serializable {
    @Enumerated(EnumType.STRING)
    @Column(name="Company_Type")
    private CompanyType companyType;

}
