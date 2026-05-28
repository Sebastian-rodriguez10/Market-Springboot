package com.adso.market.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.categoryDTO.CategoryNameDTO;
import com.adso.market.dto.employesDTO.RegisterEmpleoyesDTO;
import com.adso.market.entity.Employees;
import com.adso.market.repository.EmployeesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleoyeesService {

    private final EmployeesRepository employeesRepository;

    public HttpGlobalResponse<RegisterEmpleoyesDTO> postEmployes(@RequestBody RegisterEmpleoyesDTO registerEmpleoyesDTO) {

        Employees reDto = new Employees();

        reDto.setIdentification(registerEmpleoyesDTO.getIdentification());
        reDto.setName(registerEmpleoyesDTO.getName());
        reDto.setRolePosition(registerEmpleoyesDTO.getRole());
        reDto.setStartDate(registerEmpleoyesDTO.getStarDate());
        employeesRepository.save(reDto);

        HttpGlobalResponse<RegisterEmpleoyesDTO> response = new HttpGlobalResponse<>();
        response.setData(registerEmpleoyesDTO);
        response.setMessage("se creo");

        return response;
    }
    
}
