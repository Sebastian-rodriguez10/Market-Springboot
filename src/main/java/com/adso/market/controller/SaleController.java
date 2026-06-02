package com.adso.market.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.employes.RegisterEmpleoyesDTO;
import com.adso.market.dto.sale.SaleEmployeeRequesDTO;
import com.adso.market.dto.saleDetail.RegisterSaleDetailDTO;
import com.adso.market.service.SaleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;
    
    @PostMapping("/post-sale")
    public ResponseEntity<MessageResponseDTO> postMethodName(@Valid @RequestBody RegisterSaleDetailDTO registerSaleDetailDTO) {
        
        try {
            
            MessageResponseDTO response = saleService.processSale(registerSaleDetailDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }
    
}
