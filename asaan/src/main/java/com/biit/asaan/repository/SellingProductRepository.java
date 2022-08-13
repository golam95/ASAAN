package com.biit.asaan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.biit.asaan.model.SellingProduct;


@Repository 
public interface SellingProductRepository extends JpaRepository<SellingProduct, Long> {
	
	SellingProduct findByproductName(String productName);
	
	@Query(value = "select b.productname,b.productcategory,c.categoryname,b.price,b.date,b.govt_price,b.id from sellproduct b INNER JOIN category c ON b.productcategory=c.id order by b.productname ASC", nativeQuery = true)
	List<Object[]> getSellProductList();
	
}
