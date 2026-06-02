package com.adso.market.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.saleDetail.RegisterSaleDetailDTO;
import com.adso.market.entity.Employees;
import com.adso.market.entity.Sale;
import com.adso.market.entity.SaleDetail;
import com.adso.market.entity.Products;
import com.adso.market.repository.EmployeesRepository;
import com.adso.market.repository.ProductsRepository; // Añadido
import com.adso.market.repository.SaleDetailsRepository;
import com.adso.market.repository.SaleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
    
    private final SaleRepository saleRepository;
    private final SaleDetailsRepository saleDetailsRepository;
    private final ProductsRepository productsRepository; // Inyectado para buscar el producto
    private final EmployeesRepository employeesRepository; 

    @Transactional
    public MessageResponseDTO processSale(RegisterSaleDetailDTO registerSaleDetailDTO) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Employees> optionalEmployees = employeesRepository.findById(registerSaleDetailDTO.getIdEmployees());

        if (optionalEmployees.isPresent()) {
            
            Optional<Products> optionalProduct = productsRepository.findById(registerSaleDetailDTO.getIdProduct());
            if (!optionalProduct.isPresent()) {
                response.setMessage("producto no encontrado");
                return response;
            }
            
            Products productFound = optionalProduct.get();

            Sale sale = new Sale();
            sale.setIdEployee(optionalEmployees.get());
            sale.setSaleDate(LocalDateTime.now());
            sale.setTotal(BigDecimal.ZERO); 
            Sale savedSale = saleRepository.save(sale);
            
            saleDetailsRepository.postReduceStock(productFound.getId(), registerSaleDetailDTO.getQuiantity());
            
            BigDecimal quantityDecimal = new BigDecimal(registerSaleDetailDTO.getQuiantity());
            BigDecimal calculatedSubtotal = registerSaleDetailDTO.getUnitPrice().multiply(quantityDecimal);

            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setSale(savedSale); 
            saleDetail.setProduct(productFound); 
            saleDetail.setQuantity(registerSaleDetailDTO.getQuiantity());
            saleDetail.setUnitPrice(registerSaleDetailDTO.getUnitPrice());
            saleDetail.setSubtotal(calculatedSubtotal); 
            
            saleDetailsRepository.save(saleDetail);
            
            saleRepository.pacalcularTotalesventa(savedSale.getId());
            
            response.setMessage("Venta procesada con exito");
        } else {
            response.setMessage("empleado no encontrado");
        }
        
        return response;
    }
}
