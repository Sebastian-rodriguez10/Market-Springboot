package com.adso.market.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.inventory.InventoryDTO;
import com.adso.market.entity.Inventory;
import com.adso.market.entity.Products;
import com.adso.market.entity.Suppliers;
import com.adso.market.repository.InventoryRepository;
import com.adso.market.repository.ProductsRepository;
import com.adso.market.repository.SuppliersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductsRepository productRepository;
    private final SuppliersRepository suppliersRepository;
    private final InventoryRepository inventoryRepository;

    public MessageResponseDTO addInventory(InventoryDTO data) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Products> productFound = productRepository.findById(data.getIdProduct());

        if (productFound.isEmpty()) {
            response.setMessage("Producto no encontrado");
            return response;
        }

        Optional<Suppliers> supplierFound = suppliersRepository.findById(data.getIdSupplier());

        if (supplierFound.isEmpty()) {
            response.setMessage("Proveedor no encontrado");
            return response;
        }

        if (data.getQuantity() <= 0) {
            response.setMessage("La cantidad debe ser mayor a cero");
            return response;
        }

        Optional<Inventory> inventoryFound = inventoryRepository.findByProductsIdAndSuppliersId(data.getIdProduct(),
                data.getIdSupplier());

        if (inventoryFound.isPresent()) {

            Inventory inventory = inventoryFound.get();

            inventory.setStock(inventory.getStock() + data.getQuantity());

            if (inventory.getStock() > 0) {
                inventory.getProducts().setState(true);
                productRepository.save(inventory.getProducts());
            }

            inventoryRepository.save(inventory);

            response.setMessage("Stock actualizado correctamente");

        } else {

            Inventory inventory = new Inventory();

            inventory.setProducts(productFound.get());
            inventory.setSuppliers(supplierFound.get());
            inventory.setStock(data.getQuantity());

            inventoryRepository.save(inventory);

            response.setMessage("Registro creado correctamente");
        }

        return response;
    }
}
