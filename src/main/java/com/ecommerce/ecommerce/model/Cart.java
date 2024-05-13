package com.ecommerce.ecommerce.model;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cart_items")
public class Cart {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "cart_item_id")
    private UUID cartItemId;

    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "fk_user_id")
    // private User userId;
    @Column(name = "fk_user_id")
    private UUID userId;

    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "fk_product_id")
    // private Product prodId;
    @Column(name = "fk_product_id")
    private UUID prodId;

    @Column(name = "cart_prod_name")
    private String prodName;

    @Column(name = "cart_prod_cost")
    private long prodCost;

}
