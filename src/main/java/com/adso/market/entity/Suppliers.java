package com.adso.market.entity;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "suppliers" )
public class Suppliers {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "campo obligatorio")
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @NotNull(message = "campo obligatorio")
    @Size(min = 10, max = 10, message = "El teléfono debe tener exactamente 10 dígitos")
    @Column(name = "phone")
    private String phone;

    // @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    // private List<Products> product = new ArrayList<>();

    // id int auto_increment primary key,
    // name varchar(50) not null,
    // phone varchar(10) not null
}
