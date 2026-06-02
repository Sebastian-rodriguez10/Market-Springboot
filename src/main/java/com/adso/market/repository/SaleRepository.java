package com.adso.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.adso.market.entity.Sale;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SaleRepository extends JpaRepository<Sale, Long> {
    
    @Modifying 
    @Procedure(procedureName = "pa_calcular_totales_venta")
    void pacalcularTotalesventa(@Param("p_id_sale") Long idSale);

}
