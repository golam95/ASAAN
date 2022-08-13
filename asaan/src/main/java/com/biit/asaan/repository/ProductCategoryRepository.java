package com.biit.asaan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biit.asaan.model.ProdutCategory;

@Repository 
public interface ProductCategoryRepository extends JpaRepository<ProdutCategory, Long> {

	ProdutCategory findBycategoryName(String categoryName);

}
