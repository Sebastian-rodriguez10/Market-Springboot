package com.adso.market.service;

import org.springframework.stereotype.Service;

import com.adso.market.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
}
