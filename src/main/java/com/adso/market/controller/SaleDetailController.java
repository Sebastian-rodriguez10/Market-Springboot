package com.adso.market.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.employes.RegisterEmpleoyesDTO;
import com.adso.market.dto.saleDetail.RegisterSaleDetailDTO;
import com.adso.market.service.SaleDetalilService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sale-detail")
@RequiredArgsConstructor
public class SaleDetailController {

    private final SaleDetalilService saleDetalilService;
    
    @PostMapping("/register-sale")
    public ResponseEntity<MessageResponseDTO> resgisterSaleDetails(@Valid @RequestBody RegisterSaleDetailDTO registerSaleDetailDTO) {
        try {
            MessageResponseDTO response = saleDetalilService.resgisterSaleDetails(registerSaleDetailDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }
}
