package com.adso.market.service;

import org.springframework.stereotype.Service;

import com.adso.market.repository.SaleDetailsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleDetalilService {
    
    private final SaleDetailsRepository saleDetailsRepository;
}
