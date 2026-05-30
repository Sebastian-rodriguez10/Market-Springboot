package com.adso.market.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.market.dto.HttpGlobalResponse;
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
    public HttpGlobalResponse<RegisterEmpleoyesDTO> postEmployes(@Valid @RequestBody RegisterEmpleoyesDTO registerEmpleoyesDTO) {
        return empleoyeesService.postEmployes(registerEmpleoyesDTO);
    }

    @GetMapping("/get-all")
    public List<RegisterEmpleoyesDTO> getAllEmployees() {
        return empleoyeesService.getAllEmployees();
    }

    @GetMapping("/search/{id}")
    public HttpGlobalResponse<RegisterEmpleoyesDTO> searchEmployee(@PathVariable Long id) {
        return empleoyeesService.searchEmployee(id);
    }

    @DeleteMapping("/delete/{id}")
    public HttpGlobalResponse<String> deleteEmployee(@PathVariable Long id) {
        return empleoyeesService.deleteEmployee(id);
    }

    @PutMapping("/update/{id}")
    public HttpGlobalResponse<ResponseEmployeesDTO> putEmployee(@PathVariable Long id, @Valid @RequestBody RegisterEmpleoyesDTO updateDTO) {
        return empleoyeesService.putEmployee(id, updateDTO);
    }

}
