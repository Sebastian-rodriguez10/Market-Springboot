package com.adso.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adso.market.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    
}
