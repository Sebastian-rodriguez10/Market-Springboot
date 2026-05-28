package com.adso.market.service;

import org.springframework.stereotype.Service;

import com.adso.market.repository.SuppliersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuppliersService {
    
    private final SuppliersRepository suppliersRepository;
}
