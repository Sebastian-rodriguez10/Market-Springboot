package com.adso.market.dto.employes;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ResponseEmployeesDTO {
    
    private Long id; // Aquí incluimos el ID que no tiene tu otro DTO

    private String identification;

    private String name;

    private String role;

    private LocalDate starDate;
}
