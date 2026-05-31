package com.adso.market.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adso.market.entity.Category;


@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long>{
    @Procedure(procedureName = "get_category_with_products")
    List<Object[]> getCategoryWithProducts(@Param("p_category_id") Long id);

    Optional<Category> findByName(String name);
}