package com.codeid.be_eshopay.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@MappedSuperclass
public abstract class AbstractEntity {
    @CreationTimestamp
    @Column(name = "created_date")
    private Instant createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private Instant modifiedDate;
}
