package com.adso.market.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.category.CategoryNameDTO;
import com.adso.market.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("get-category/{id}")
    public List<Object[]> getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping("/post-category")
    public HttpGlobalResponse<CategoryNameDTO> postCategory(@Valid @RequestBody CategoryNameDTO nombreCategoria) {
        return categoryService.postCategory(nombreCategoria);
    }

    @PutMapping("/put-category/{id}")
    public HttpGlobalResponse<CategoryNameDTO> putCategory(@PathVariable Long id,@Valid @RequestBody CategoryNameDTO name) {
        return categoryService.updateCategory(id, name);

    }

    @DeleteMapping("/delete-category/{id}")
    public HttpGlobalResponse<MessageResponseDTO> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);

    }

}
