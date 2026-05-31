package com.adso.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.product.ProductDTO;
import com.adso.market.entity.Category;
import com.adso.market.entity.Products;
import com.adso.market.repository.CategoryRepository;
import com.adso.market.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productRepository;
    private final CategoryRepository categoryRepository;

    // GET
    public List<ProductDTO> getProducts() {
        List<ProductDTO> listProducts = new ArrayList<>();
        List<Products> productsFound = productRepository.findAll();

        for (Products products : productsFound) {
            ProductDTO pro = new ProductDTO();
            pro.setName(products.getName());
            pro.setBarCode(products.getBarCode());
            pro.setPrice(products.getPrice());
            pro.setIdCategory(products.getCategory().getId());
            listProducts.add(pro);
        }

        return listProducts;
    }

    // POST
    public HttpGlobalResponse<ProductDTO> postProduct(ProductDTO data) {

        Optional<Category> optionalCategory = categoryRepository.findById(data.getIdCategory());

        HttpGlobalResponse<ProductDTO> response = new HttpGlobalResponse<>();

        if (optionalCategory.isPresent()) {

            Products product = new Products();

            product.setName(data.getName());
            product.setPrice(data.getPrice());
            product.setBarCode(data.getBarCode());
            product.setState(true);
            product.setCategory(optionalCategory.get());

            productRepository.save(product);

            response.setData(data);
            response.setMessage("producto creado correctamente");

        } else {
            response.setMessage("categoria no encontrada");
        }

        return response;
    }

    // PUT
    public HttpGlobalResponse<ProductDTO> updateProduct(Long id, ProductDTO data) {

        Optional<Products> optionalProduct = productRepository.findById(id);

        HttpGlobalResponse<ProductDTO> response = new HttpGlobalResponse<>();

        if (optionalProduct.isPresent()) {

            Products product = optionalProduct.get();

            Optional<Category> optionalCategory = categoryRepository.findById(data.getIdCategory());

            if (optionalCategory.isPresent()) {

                product.setName(data.getName());
                product.setPrice(data.getPrice());
                product.setBarCode(data.getBarCode());
                product.setCategory(optionalCategory.get());

                productRepository.save(product);

                response.setData(data);
                response.setMessage("producto actualizado correctamente");

            } else {

                response.setMessage("categoria no encontrada");
            }

        } else {

            response.setMessage("producto no encontrado");
        }

        return response;
    }

    // DELETE SOFT
    public MessageResponseDTO deleteProduct(Long id) {

        Optional<Products> optionalProduct = productRepository.findById(id);

        MessageResponseDTO response = new MessageResponseDTO();
        if (optionalProduct.isPresent()) {
            Products product = optionalProduct.get();
            product.setState(false);
            productRepository.save(product);

            response.setMessage("producto eliminado correctamente");

        } else {
            response.setMessage("producto no encontrado");
        }

        return response;
    }
}