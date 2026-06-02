package com.adso.market.dto.saleDetail;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RegisterSaleDetailDTO {
    
    private Long idEmployees;

    private Long idSale;

    private Long idProduct;

    private int quiantity;

    private BigDecimal unitPrice;

}
