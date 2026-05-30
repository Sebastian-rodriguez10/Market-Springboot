package com.adso.market.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.product.ProductDTO;
import com.adso.market.entity.Products;
import com.adso.market.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping("/get-products")
    public List<Products> getProducts(){
        return productService.getProducts();
    }

    @PostMapping("/post-product")
    public HttpGlobalResponse<ProductDTO> postProduct(@Valid @RequestBody ProductDTO data){
        return productService.postProduct(data);
    }

    @PutMapping("/put-product/{id}")
    public HttpGlobalResponse<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO data){
        return productService.updateProduct(id, data);
    }

    @DeleteMapping("/delete-product/{id}")
    public HttpGlobalResponse<String> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}