package com.adso.market.dto.inventory;


import lombok.Data;

@Data
public class InventoryDTO {
    private Long idProduct;
    private Long idSupplier;
    private Integer quantity;
}
