package com.adso.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.adso.market.dto.HttpGlobalResponse;
import com.adso.market.dto.category.CategoryNameDTO;
import com.adso.market.dto.employes.RegisterEmpleoyesDTO;
import com.adso.market.dto.employes.ResponseEmployeesDTO;
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

    public List<RegisterEmpleoyesDTO> getAllEmployees() {
    List<Employees> employeesList = employeesRepository.findAll();
    
    List<RegisterEmpleoyesDTO> dtoList = new ArrayList<>();

    for (Employees emp : employeesList) {
        RegisterEmpleoyesDTO dto = new RegisterEmpleoyesDTO();
        dto.setIdentification(emp.getIdentification());
        dto.setName(emp.getName());
        dto.setRole(emp.getRolePosition());
        dto.setStarDate(emp.getStartDate());
        
        dtoList.add(dto);
    }
    return dtoList;
}

    public HttpGlobalResponse<RegisterEmpleoyesDTO> searchEmployee(Long id) {
    
    HttpGlobalResponse<RegisterEmpleoyesDTO> employeeFound = new HttpGlobalResponse<>();

    
    Optional<Employees> employeeNew = employeesRepository.findById(id);
    
    
    if (employeeNew.isEmpty()) {
        employeeFound.setMessage("Empleado no encontrado");
        return employeeFound;
    }

    
    Employees emp = employeeNew.get();

    
    RegisterEmpleoyesDTO dto = new RegisterEmpleoyesDTO();
    dto.setIdentification(emp.getIdentification());
    dto.setName(emp.getName());
    dto.setRole(emp.getRolePosition());
    dto.setStarDate(emp.getStartDate());

    
    employeeFound.setData(dto);
    employeeFound.setMessage("Empleado encontrado con éxito");

    return employeeFound;
}

    public HttpGlobalResponse<String> deleteEmployee(Long id) {
    HttpGlobalResponse<String> response = new HttpGlobalResponse<>();
    Optional<Employees> employeeNew = employeesRepository.findById(id);
    
    if (employeeNew.isEmpty()) {
        response.setMessage("Empleado no encontrado");
        response.setData(null);
        return response;
    }

    employeesRepository.deleteById(id);
    response.setMessage("Empleado eliminado con éxito");
    response.setData("ID eliminado: " + id);
    return response;
    }

    public HttpGlobalResponse<ResponseEmployeesDTO> putEmployee(Long id, RegisterEmpleoyesDTO updateDTO) {
    HttpGlobalResponse<ResponseEmployeesDTO> response = new HttpGlobalResponse<>();
    Optional<Employees> employeeNew = employeesRepository.findById(id);
    
    if (employeeNew.isEmpty()) {
        response.setMessage("Empleado no encontrado");
        response.setData(null);
        return response;
    }

    Employees employeeFinal = employeeNew.get();
    
    employeeFinal.setIdentification(updateDTO.getIdentification());
    employeeFinal.setName(updateDTO.getName());
    employeeFinal.setRolePosition(updateDTO.getRole());
    employeeFinal.setStartDate(updateDTO.getStarDate());

    employeesRepository.save(employeeFinal);
    
    ResponseEmployeesDTO responseDTO = new ResponseEmployeesDTO();
    responseDTO.setId(employeeFinal.getId()); 
    responseDTO.setIdentification(employeeFinal.getIdentification());
    responseDTO.setName(employeeFinal.getName());
    responseDTO.setRole(employeeFinal.getRolePosition());
    responseDTO.setStarDate(employeeFinal.getStartDate());
    
    response.setMessage("Empleado actualizado con éxito");
    response.setData(responseDTO);

    return response;
}
}
