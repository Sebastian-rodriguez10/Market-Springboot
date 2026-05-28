package com.adso.market.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_product")
    private Products products;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_supplier")
    Private Suppliers suppliers;

    @Column(name = "stock", columnDefinition = "int default 0")
    private int stock = 0;

    @Column(name = "entry_date")
    private LocalDate entryDate;
}
