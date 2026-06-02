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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Object[]>> getCategory(@PathVariable Long id) {
        List<Object[]> response = categoryService.getCategory(id);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/post-category")
    public ResponseEntity<HttpGlobalResponse<CategoryNameDTO>> postCategory(@Valid @RequestBody CategoryNameDTO nombreCategoria) {
        HttpGlobalResponse<CategoryNameDTO>  response = categoryService.postCategory(nombreCategoria);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/put-category/{id}")
    public ResponseEntity<HttpGlobalResponse<CategoryNameDTO>> putCategory(@PathVariable Long id,@Valid @RequestBody CategoryNameDTO name) {
        HttpGlobalResponse<CategoryNameDTO>  response = categoryService.updateCategory(id, name);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<HttpGlobalResponse<MessageResponseDTO>> deleteCategory(@PathVariable Long id) {
        HttpGlobalResponse<MessageResponseDTO> response = categoryService.deleteCategory(id);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

}
