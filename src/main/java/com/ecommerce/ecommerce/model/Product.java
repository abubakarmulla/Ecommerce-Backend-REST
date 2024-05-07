package com.ecommerce.ecommerce.model;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "product_id")
    private UUID prodId;

    @Column(name = "product_name", nullable = false)
    private String prodName;

    @Column(name = "product_description")
    private String prodDesc;

    @Column(name = "product_cost", nullable = false)
    private long prodCost;
}
