package com.codeid.be_eshopay.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions", schema = "fintech")
public class Transaction extends AbstractEntity {

    @Id
    @SequenceGenerator(
            name = "transaction_seq_gen",
            sequenceName = "fintech.transactions_trac_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_seq_gen"
    )
    @Column(name = "trac_id")
    private Long id;

    @Column(name = "trac_no")
    private String tracNumber;

    @Column(name = "trac_noref")
    private String tracRefNumber;

    @Column(name = "debet")
    private Double debit;

    @Column(name = "credit")
    private Double credit;

    @Column(name = "trac_type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "from_account", referencedColumnName = "account_id")
    private Account fromAccount;

    @OneToOne
    @JoinColumn(name = "to_account", referencedColumnName = "account_id")
    private Account toAccount;
}
