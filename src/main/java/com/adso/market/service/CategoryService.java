package com.adso.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.category.CategoryNameDTO;
import com.adso.market.entity.Category;
import com.adso.market.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<Object[]> getCategory(Long id) {

        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            List<Object[]> res = new ArrayList<>();
            res.add(new Object[]{"La categoria consultada no existe"});
            return res;
        }

        List<Object[]> response = categoryRepository.getCategoryWithProducts(id);
        return response;

    }

    public HttpGlobalResponse<CategoryNameDTO> postCategory(CategoryNameDTO name) {

        Optional<Category> categoryFound = categoryRepository.findByName(name.getName());
        HttpGlobalResponse<CategoryNameDTO> response = new HttpGlobalResponse<>();
        if (name == null || name.getName().isEmpty()) {
            response.setData(name);
            response.setMessage("la categoria no puede ser null ni puede estar vacia");
            return response;
        }
        if (categoryFound.isPresent()) {
            response.setData(name);
            response.setMessage("Esta categoria ya existe");
            return response;
        }

        Category category = new Category();

        category.setName(name.getName());
        categoryRepository.save(category);
        response.setData(name);
        response.setMessage("categoria creada correctamente");
        return response;

    }

    public HttpGlobalResponse<CategoryNameDTO> updateCategory(Long id, CategoryNameDTO name) {
        
        HttpGlobalResponse<CategoryNameDTO> response = new HttpGlobalResponse<>();
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            response.setMessage("Categoria no encontrada");
            return response;
        }

        Category newCategory = category.get();

        categoryRepository.save(newCategory);

        response.setData(name);
        response.setMessage("categoria actualizada correctamente");

        return response;
    }

    public HttpGlobalResponse<MessageResponseDTO> deleteCategory(Long id){

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        HttpGlobalResponse<MessageResponseDTO> response = new HttpGlobalResponse<>();
        MessageResponseDTO message = new MessageResponseDTO();

        if(optionalCategory.isPresent()){
            message.setMessage("ok");
            Category category = optionalCategory.get();
            categoryRepository.delete(category);
            response.setData(message);
            response.setMessage("categoria eliminada correctamente");
        } else {
            message.setMessage("error");
            response.setData(message);
            response.setMessage("categoria no encontrada");
        }

        return response;
}
}
