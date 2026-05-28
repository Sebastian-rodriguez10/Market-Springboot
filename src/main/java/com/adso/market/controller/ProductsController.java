package com.adso.market.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.RegisterRequestProductDTO;
import com.adso.market.service.ProductsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    
    private final ProductsService productsService;

    @PostMapping("/create")
    public MessageResponseDTO createProduct(@Valid @RequestBody RegisterRequestProductDTO requestDTO) {
        MessageResponseDTO response = productsService.createProduct(requestDTO);
        
        return response;
    }

    // @GetMapping("/get-user")
    // public List<ResponseListUserDTO> getUserDTO() {
    //     List<ResponseListUserDTO> responselistUsers = userServica.getUserDTOs();
    //     return responselistUsers;
    // }

    // @GetMapping("/get-user-code/{id}")
    // public HttpGlobalResponse<ResponseUser> serchUser(@PathVariable Long id) {
    //     HttpGlobalResponse<ResponseUser> response = userServica.serchUser(id);

    //     return response;
    // }
    
    // @DeleteMapping("/delete/{id}")
    // public MessageResponse deleteUser(@PathVariable Long id){
    //     MessageResponse response = userServica.deleteUser(id);
        
    //     return response;
    // }

    // @PutMapping("/path/{id}")
    // public MessageResponse putUser(@PathVariable Long id, @RequestBody ResponseUser usResponseUser) {

    //     MessageResponse response = userServica.putUser(id,usResponseUser);
    //     return response;
    // }
}
