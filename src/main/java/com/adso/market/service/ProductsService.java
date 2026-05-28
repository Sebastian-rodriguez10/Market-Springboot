package com.adso.market.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.RegisterRequestProductDTO;
import com.adso.market.entity.Category;
import com.adso.market.entity.Products;
import com.adso.market.dto.RegisterRequestProductDTO;
import com.adso.market.repository.CategoryRepository;
import com.adso.market.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {
    
    private final ProductsRepository productsRepository;

    private final CategoryRepository categoryRepository;

    public MessageResponseDTO createProduct(RegisterRequestProductDTO requestDTO) {
        
        MessageResponseDTO response = new MessageResponseDTO();
        
        Category category = categoryRepository.findById(requestDTO.getIdCategory())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));
        
        Products producto = new Products();
        producto.setName(requestDTO.getName());
        producto.setPrice(requestDTO.getPrice());
        producto.setBarCode(requestDTO.getBarCode());
        producto.setState(requestDTO.getState());
        producto.setCategory(category); 

        
        productsRepository.save(producto);

        
        response.setMessage("creado");
        return response;
    }
}
