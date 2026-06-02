package com.adso.market.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.market.controller.SaleDetailController;
import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.saleDetail.RegisterSaleDetailDTO;
import com.adso.market.entity.Products;
import com.adso.market.entity.Sale;
import com.adso.market.entity.SaleDetail;
import com.adso.market.repository.ProductsRepository;
import com.adso.market.repository.SaleDetailsRepository;
import com.adso.market.repository.SaleRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleDetalilService {
    
    private final SaleDetailsRepository saleDetailsRepository;
    private final SaleRepository saleRepository;
    private final ProductsRepository productsRepository;

    @Transactional
    public MessageResponseDTO resgisterSaleDetails(RegisterSaleDetailDTO registerSaleDetailDTO) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Sale> optionalSale = saleRepository.findById(registerSaleDetailDTO.getIdSale());
        Optional<Products> optionaProduct = productsRepository.findById(registerSaleDetailDTO.getIdProduct());

        if(optionaProduct.isPresent()&&optionalSale.isPresent()){

            Products productsFound = optionaProduct.get();

            saleDetailsRepository.postReduceStock(productsFound.getId(), registerSaleDetailDTO.getQuiantity());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setSale(optionalSale.get());
            saleDetail.setProduct(optionaProduct.get());
            saleDetail.setQuantity(registerSaleDetailDTO.getQuiantity());
            saleDetail.setUnitPrice(registerSaleDetailDTO.getUnitPrice());
            saleDetailsRepository.save(saleDetail);


            response.setMessage("se creo con exito");
            return response;
        }else{
            response.setMessage("Hubo un error no se encontro");
            return response;
        }
        
        
    }
}
