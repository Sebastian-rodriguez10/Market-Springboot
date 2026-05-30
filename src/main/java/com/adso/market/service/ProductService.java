package com.adso.market.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.market.dto.HttpGlobalResponse;
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
    public List<Products> getProducts() {
        return productRepository.findAll();
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
    public HttpGlobalResponse<String> deleteProduct(Long id) {

        Optional<Products> optionalProduct = productRepository.findById(id);

        HttpGlobalResponse<String> response = new HttpGlobalResponse<>();

        if (optionalProduct.isPresent()) {
            Products product = optionalProduct.get();
            product.setState(false);
            productRepository.save(product);

            response.setData("ok");
            response.setMessage("producto eliminado correctamente");

        } else {
            response.setData("error");
            response.setMessage("producto no encontrado");
        }

        return response;
    }
}