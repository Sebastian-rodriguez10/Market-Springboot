package com.adso.market.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.MessageResponseDTO;
import com.adso.market.dto.employes.RegisterEmpleoyesDTO;
import com.adso.market.dto.employes.ResponseEmployeesDTO;
import com.adso.market.service.EmpleoyeesService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmpleoyeesService empleoyeesService;

    @PostMapping("/post-employes")
    public ResponseEntity<HttpGlobalResponse<RegisterEmpleoyesDTO>> postEmployes(@Valid @RequestBody RegisterEmpleoyesDTO registerEmpleoyesDTO) {
        try {
            HttpGlobalResponse<RegisterEmpleoyesDTO> response = empleoyeesService.postEmployes(registerEmpleoyesDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }
    
    @GetMapping("/get-all")
    public ResponseEntity <List<RegisterEmpleoyesDTO>> getAllEmployees() {
        try {
            List<RegisterEmpleoyesDTO> response = empleoyeesService.getAllEmployees();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }

    @GetMapping("/search/{id}")
    public ResponseEntity <HttpGlobalResponse<RegisterEmpleoyesDTO>> searchEmployee(@PathVariable Long id) {
        try {
            HttpGlobalResponse<RegisterEmpleoyesDTO> response = empleoyeesService.searchEmployee(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <MessageResponseDTO> deleteEmployee(@PathVariable Long id) {
        try {
            MessageResponseDTO response = empleoyeesService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <HttpGlobalResponse<ResponseEmployeesDTO>> putEmployee(@PathVariable Long id, @Valid @RequestBody RegisterEmpleoyesDTO updateDTO) {
        try {
            HttpGlobalResponse<ResponseEmployeesDTO> response = empleoyeesService.putEmployee(id, updateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }

    @GetMapping("get-employer-rol/{rol}")
    public ResponseEntity<List<Object[]>> getEmployesByRol(@Valid @PathVariable String rol) {
        try {
            List<Object[]> response = empleoyeesService.getEmployesByRol(rol);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }

}
