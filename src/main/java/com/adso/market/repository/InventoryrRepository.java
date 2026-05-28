package com.adso.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adso.market.entity.Inventory;

@Repository
public interface InventoryrRepository extends JpaRepository<Inventory, Long> {
    
}
