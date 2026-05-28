package com.adso.market.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.categoryDTO.CategoryNameDTO;
import com.adso.market.entity.Category;
import com.adso.market.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<Object[]> getCategory(Long id) {
        List<Object[]> response = categoryRepository.getCategoryWithProducts(id);
        return response;
    }

    public HttpGlobalResponse<CategoryNameDTO> postCategory(CategoryNameDTO name) {
        Category category = new Category();
        // CategoryNameDTO categoryNameDTO = new CategoryNameDTO();
        category.setName(name.getName());
        categoryRepository.save(category);
        HttpGlobalResponse<CategoryNameDTO> response = new HttpGlobalResponse<>();
        response.setData(name);
        response.setMessage("categoria creada correctamente");
        return response;

    }

    public HttpGlobalResponse<CategoryNameDTO> updateCategory(Long id, CategoryNameDTO name) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("categoria no encontrada"));

        category.setName(name.getName());

        categoryRepository.save(category);

        HttpGlobalResponse<CategoryNameDTO> response = new HttpGlobalResponse<>();

        response.setData(name);
        response.setMessage("categoria actualizada correctamente");

        return response;
    }

    public HttpGlobalResponse<String> deleteCategory(Long id){

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        HttpGlobalResponse<String> response = new HttpGlobalResponse<>();

        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            categoryRepository.delete(category);
            response.setData("ok");
            response.setMessage("categoria eliminada correctamente");
        } else {
            response.setData("error");
            response.setMessage("categoria no encontrada");
        }

        return response;
}
}
