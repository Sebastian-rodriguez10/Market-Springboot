package com.adso.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adso.market.entity.Employees;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface EmployeesRepository extends JpaRepository<Employees, Long>{
    @Procedure(procedureName = "pa_list_employees_by_role")
    List<Object[]> paListEmployeesByRole(@Param("rol") String rol);
}
