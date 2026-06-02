package com.adso.market.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.inventory.InventoryDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.adso.market.service.InventoryService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/add-inventory")
    public ResponseEntity<MessageResponseDTO> addInventory(@RequestBody InventoryDTO request) {
        MessageResponseDTO response = inventoryService.addInventory(request);
        try {

            if (response.getMessage().equalsIgnoreCase("Registro creado correctamente")) {
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            
        }
    }
    
}
