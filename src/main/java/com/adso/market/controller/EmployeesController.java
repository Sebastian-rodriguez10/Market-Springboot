package com.adso.market.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.categoryDTO.CategoryNameDTO;
import com.adso.market.dto.employesDTO.RegisterEmpleoyesDTO;
import com.adso.market.service.EmpleoyeesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmpleoyeesService empleoyeesService;
    
    @PostMapping("/post-employes")
    public HttpGlobalResponse<RegisterEmpleoyesDTO> postEmployes(@RequestBody RegisterEmpleoyesDTO registerEmpleoyesDTO) {
        return empleoyeesService.postEmployes(registerEmpleoyesDTO);
    }
}
