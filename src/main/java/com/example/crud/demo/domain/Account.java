package com.example.crud.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(of = "id")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person person;
    private String numberAccount;
    private BigDecimal balance;
}
