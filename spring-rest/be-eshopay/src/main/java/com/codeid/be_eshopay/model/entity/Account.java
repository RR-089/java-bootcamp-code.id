package com.codeid.be_eshopay.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts", schema = "fintech")
public class Account extends AbstractEntity {
    @Id
    @SequenceGenerator(
            name = "account_seq_gen",
            sequenceName = "fintech.accounts_account_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_seq_gen"
    )
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_no")
    private String accountNumber;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "currency")
    private String currency;

    @OneToOne
    @JoinColumn(name = "fin_code", referencedColumnName = "fint_code")
    private Fintech fintech;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}
