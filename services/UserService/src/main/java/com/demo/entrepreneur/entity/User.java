package com.demo.entrepreneur.entity;

import com.demo.entrepreneur.enumeration.TaxGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "apprx_income_usd", nullable = false)
    private Integer approximateIncomeInUsd;
    @Column(name = "tax_group", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaxGroup taxGroup;
}
