package com.adso.market.dto.employesDTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RegisterEmpleoyesDTO {
    private String identification;

    private String name;

    private String role;

    private LocalDate starDate;
}
