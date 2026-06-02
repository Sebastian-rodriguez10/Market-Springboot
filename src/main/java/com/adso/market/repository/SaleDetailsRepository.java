package com.adso.market.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.adso.market.entity.SaleDetail;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SaleDetailsRepository extends JpaRepository<SaleDetail, Long> {
    
    @Procedure(procedureName = "pa_reduce_stock")
    void postReduceStock(@Param("product") Long product,@Param("quanty_value") int quantyValue);
    
}
