package com.adso.market.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.product.ProductDTO;
import com.adso.market.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping("/get-products")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<ProductDTO> response = productService.getProducts();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/post-product")
    public ResponseEntity<HttpGlobalResponse<ProductDTO>> postProduct(@Valid @RequestBody ProductDTO data){
        HttpGlobalResponse<ProductDTO> response = productService.postProduct(data);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/put-product/{id}")
    public ResponseEntity<HttpGlobalResponse<ProductDTO>> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO data){
        HttpGlobalResponse<ProductDTO> response = productService.updateProduct(id, data);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<MessageResponseDTO> deleteProduct(@PathVariable Long id){
        MessageResponseDTO response = productService.deleteProduct(id);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}