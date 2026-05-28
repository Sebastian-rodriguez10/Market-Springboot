package com.adso.market.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.categoryDTO.CategoryNameDTO;
import com.adso.market.entity.Category;
import com.adso.market.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Object[]> getCategory(Long id){
        List<Object[]> response = categoryRepository.getCategoryWithProducts(id);
        return response;
    }

    public HttpGlobalResponse<CategoryNameDTO> postCategory (CategoryNameDTO name){
        Category category = new Category();
        //CategoryNameDTO categoryNameDTO = new CategoryNameDTO();
        category.setName(name.getName());
        categoryRepository.save(category);
        HttpGlobalResponse<CategoryNameDTO> response = new HttpGlobalResponse<>();
        response.setData(name);
        response.setMessage("categoria creada correctamente");
        return response;

        
    }
}
