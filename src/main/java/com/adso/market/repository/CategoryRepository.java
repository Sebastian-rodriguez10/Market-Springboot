package com.adso.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adso.market.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    @Procedure(procedureName = "get_category_with_products")
    List<Object[]> getCategoryWithProducts(@Param("p_category_id") Long id);
}