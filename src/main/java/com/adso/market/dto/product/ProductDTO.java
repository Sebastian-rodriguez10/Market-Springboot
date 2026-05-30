package com.adso.market.dto.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;

    private BigDecimal price;

    private String barCode;

    private Long idCategory;
}