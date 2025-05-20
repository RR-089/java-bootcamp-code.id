package com.codeid.be_eshopay.model.entity;

import com.codeid.be_eshopay.constant.FintechType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fintechs", schema = "fintech")
public class Fintech {
    @Id
    @Column(name = "fint_code")
    private String code;

    @Column(name = "fint_name")
    private String name;

    @Column(name = "fint_short_name")
    private String shortName;

    @Column(name = "fint_type")
    private FintechType type;
}
