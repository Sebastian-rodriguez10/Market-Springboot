package com.adso.market.entity;

import java.math.BigDecimal;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sale_detail")
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_sale")
    private Sale sale;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Products product;

    @NotNull
    @Column
    private int quantity;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;
}
