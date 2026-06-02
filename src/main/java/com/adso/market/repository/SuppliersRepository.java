package com.adso.market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adso.market.entity.Suppliers;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Long> {
    Optional<Suppliers> findByNit(String nit);   
}
