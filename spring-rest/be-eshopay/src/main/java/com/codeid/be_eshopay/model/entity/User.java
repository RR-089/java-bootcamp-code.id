package com.codeid.be_eshopay.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "person")
public class User extends AbstractEntity {

    @Id
    @SequenceGenerator(
            name = "user_seq_gen",
            sequenceName = "person.users_user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq_gen"
    )
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_handphone")
    private String handphone;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;
}
