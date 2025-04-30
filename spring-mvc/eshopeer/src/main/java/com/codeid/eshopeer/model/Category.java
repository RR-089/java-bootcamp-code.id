package com.codeid.eshopeer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories", schema = "oe")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotBlank(message = "Category name is required")
    @Size(min = 3, max = 15, message = "Category name must be between 3 and 15 characters long")
    @Column(name = "category_name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "picture", columnDefinition = "BYTEA")
    private byte[] picture;

}
