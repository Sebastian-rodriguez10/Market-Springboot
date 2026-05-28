package com.adso.market.service;

import org.springframework.stereotype.Service;

import com.adso.market.repository.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
    
    private final SaleRepository saleRepository;
}
