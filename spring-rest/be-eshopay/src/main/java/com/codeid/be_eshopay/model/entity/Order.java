package com.codeid.be_eshopay.model.entity;

import com.codeid.be_eshopay.constant.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders", schema = "oe")
public class Order extends AbstractEntity {
    @Id
    @SequenceGenerator(
            name = "order_seq_gen",
            sequenceName = "oe.orders_order_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_seq_gen"
    )
    @Column(name = "order_Id")
    private Long id;

    @CreationTimestamp
    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "required_date")
    private LocalDate requiredDate;

    @Column(name = "shipped_date")
    private LocalDate shippedDate;

    @Column(name = "ship_via")
    private Integer shipVia;

    @Column(name = "freight")
    private Double freight;

    @Column(name = "ship_name")
    private String shipName;

    @Column(name = "total_discount")
    private Double totalDiscount;

    @Column(name = "total_amount")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @OneToOne
    @JoinColumn(name = "transac_no", referencedColumnName = "trac_no")
    private Transaction transaction;

    @Column(name = "transac_date")
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    // Unused for now
    //@ManyToOne
    //@JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    //private Employee employee;

    @JsonIgnoreProperties("order")
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

}
