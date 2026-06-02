package com.adso.market.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.saleDetail.RegisterSaleDetailDTO;
import com.adso.market.entity.Employees;
import com.adso.market.entity.Sale;
import com.adso.market.repository.EmployeesRepository;
import com.adso.market.repository.SaleDetailsRepository;
import com.adso.market.repository.SaleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
    
    private final SaleRepository saleRepository;
    private final SaleDetalilService saleDetailService;
    private final SaleDetailsRepository saleDetailsRepository;
    private final EmployeesRepository employeesRepository; 
    @Transactional
    public MessageResponseDTO processSale(RegisterSaleDetailDTO registerSaleDetailDTO) {
        MessageResponseDTO response = new MessageResponseDTO();

        
        Optional<Employees> optionalEmployees = employeesRepository.findById(registerSaleDetailDTO.getIdEmployees());

        
        if (optionalEmployees.isPresent()) {
            
            
            Sale sale = new Sale();
            sale.setIdEployee(optionalEmployees.get());
            sale.setSaleDate(LocalDateTime.now());
            sale.setTotal(BigDecimal.ZERO); 
            
            Sale savedSale = saleRepository.save(sale);
            
            registerSaleDetailDTO.setIdSale(savedSale.getId());
            
            saleRepository.pa_calcular_totales_venta(savedSale.getId());
            saleDetailsRepository.postReduceStock(registerSaleDetailDTO.getIdProduct(), registerSaleDetailDTO.getQuiantity());
            
            
            response.setMessage("Venta procesada con exito");
        } else {
            
            response.setMessage("empleado no encontrado");
        }
        
        return response;
    }
}
