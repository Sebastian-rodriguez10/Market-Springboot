package com.adso.market.service;

import org.springframework.stereotype.Service;

import com.adso.market.repository.EmployeesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleoyeesService {

    private final EmployeesRepository employeesRepository;
    
}
