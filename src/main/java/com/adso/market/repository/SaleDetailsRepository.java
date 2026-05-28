package com.adso.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adso.market.entity.SaleDetail;

@Repository
public interface SaleDetailsRepository extends JpaRepository<SaleDetail, Long> {
    
}
