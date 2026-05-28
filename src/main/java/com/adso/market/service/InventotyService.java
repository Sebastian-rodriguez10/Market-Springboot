package com.adso.market.service;

import org.springframework.stereotype.Service;

import com.adso.market.repository.InventoryrRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventotyService {
    
    private final InventoryrRepository inventoryrRepository;
}
