package com.adso.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.suppliers.SupplierDTO;
import com.adso.market.service.SuppliersService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SuppliersController {
    private final SuppliersService suppliersService;

    @GetMapping("/get-suppliers")
    public ResponseEntity<List<SupplierDTO>> getSuppliers() {
        List<SupplierDTO> response = suppliersService.getSuppliers();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PostMapping("/add-supplier")
    public ResponseEntity<HttpGlobalResponse<SupplierDTO>> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        HttpGlobalResponse<SupplierDTO> response = suppliersService.createSupplier(supplierDTO);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/update-supplier/{id}")
    public ResponseEntity<HttpGlobalResponse<SupplierDTO>> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        HttpGlobalResponse<SupplierDTO> response = suppliersService.updateSupplier(id, supplierDTO);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/delete-supplier/{id}")
    public ResponseEntity<MessageResponseDTO> deleteSupplier(@PathVariable Long id) {
        MessageResponseDTO response = suppliersService.deleteSupplier(id);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}