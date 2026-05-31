package com.adso.market.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "employees")
public class Employees {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo es oblicatorio")
    @Size(max = 10, message = "no puede exeder los 10")
    @Column(name = "identification")
    private String identification;

    @NotNull(message = "El campo es oblicatorio")
    @Size(max = 50, message = "no puede exeder los 50")
    @Column(name = "name")
    private String name;

    @Size(max = 50, message = "no puede exeder los 50")
    @Pattern(
        regexp = "administrador|cajero|auxiliar", 
        message = "Rol invalido'"
    )
    @NotNull(message = "El campo es oblicatorio")
    @Column(name = "role_position")
    private String rolePosition;

    @PastOrPresent(message = "La fecha de inicio debe ser de hoy o una fecha pasada")
    @Column(name = "start_date")
    private LocalDate startDate;

}
