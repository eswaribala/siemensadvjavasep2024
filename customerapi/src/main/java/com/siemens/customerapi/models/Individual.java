package com.siemens.customerapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="Individual")
@EqualsAndHashCode(callSuper = false)
public non-sealed class Individual extends Customer{
    @Column(name="DOB")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    @Column(name="Gender")
    private Gender gender;

}
