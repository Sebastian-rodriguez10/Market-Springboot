package com.adso.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.suppliers.SupplierDTO;
import com.adso.market.entity.Suppliers;
import com.adso.market.repository.SuppliersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuppliersService {

    private final SuppliersRepository suppliersRepository;

    public List<SupplierDTO> getSuppliers() {

        List<SupplierDTO> listSuppliers = new ArrayList<>();
        List<Suppliers> suppliersFound = suppliersRepository.findAll();

        for (Suppliers supplier : suppliersFound) {

            SupplierDTO dto = new SupplierDTO();
            dto.setNit(supplier.getNit());
            dto.setName(supplier.getName());
            dto.setPhone(supplier.getPhone());

            listSuppliers.add(dto);
        }

        return listSuppliers;
    }

    public HttpGlobalResponse<SupplierDTO> createSupplier(SupplierDTO data) {

        HttpGlobalResponse<SupplierDTO> response = new HttpGlobalResponse<>();
        Optional<Suppliers> supplierFound = suppliersRepository.findByNit(data.getNit());

        if (supplierFound.isPresent()) {
            response.setMessage("El NIT ya existe");
            return response;
        }

        Suppliers supplier = new Suppliers();

        supplier.setNit(data.getNit());
        supplier.setName(data.getName());
        supplier.setPhone(data.getPhone());

        suppliersRepository.save(supplier);

        response.setData(data);
        response.setMessage("Proveedor creado correctamente");

        return response;
    }

    public HttpGlobalResponse<SupplierDTO> updateSupplier(Long id, SupplierDTO data) {

        Optional<Suppliers> optionalSupplier = suppliersRepository.findById(id);

        HttpGlobalResponse<SupplierDTO> response = new HttpGlobalResponse<>();

        if (optionalSupplier.isPresent()) {

            Suppliers supplier = optionalSupplier.get();

            supplier.setNit(data.getNit());
            supplier.setName(data.getName());
            supplier.setPhone(data.getPhone());

            suppliersRepository.save(supplier);

            response.setData(data);
            response.setMessage("Proveedor actualizado correctamente");

        } else {
            response.setMessage("Proveedor no encontrado");
        }

        return response;
    }

    public MessageResponseDTO deleteSupplier(Long id) {

        Optional<Suppliers> optionalSupplier = suppliersRepository.findById(id);

        MessageResponseDTO response = new MessageResponseDTO();

        if (optionalSupplier.isPresent()) {

            suppliersRepository.deleteById(id);

            response.setMessage("Proveedor eliminado correctamente");

        } else {

            response.setMessage("Proveedor no encontrado");
        }

        return response;
    }
}