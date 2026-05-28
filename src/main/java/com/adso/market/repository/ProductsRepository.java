package com.adso.market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adso.market.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    Optional<Products> findByBarCode(String barCode);
    boolean existsByBarCode(String barCode);
    
}
