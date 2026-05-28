package com.adso.market.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Products {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo es oblicatorio")
    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @NotNull(message = "El campo es oblicatorio")
    @Digits(integer = 8, fraction = 2)
    @Column(name = "price")
    private BigDecimal price;

    @NotNull(message = "El campo es oblicatorio")
    @Size(max = 100)
    @Column(name = "barCode")
    private String barCode;

    @NotNull(message = "El campo es oblicatorio")
    @Column(name = "state")
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false) // foreign key(id_category) references category(id)
    private Category category;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Inventory> inventories = new ArrayList<>();
}