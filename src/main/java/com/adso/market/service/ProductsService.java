package com.adso.market.service;

import org.springframework.stereotype.Service;

import com.adso.market.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {
    
    private final ProductsRepository productsRepository;
}
