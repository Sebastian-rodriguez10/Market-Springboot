package com.adso.market.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RegisterRequestProductDTO {
    
    private String name;

    private BigDecimal price;

    private Boolean state;

    private String barCode;

    private Long idCategory;
}
