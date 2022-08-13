package com.biit.asaan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.biit.asaan.model.BuyingProduct;

@Repository
public interface BuyingProductRepository extends JpaRepository<BuyingProduct, Long> {

	BuyingProduct findByproductName(String productName);

	BuyingProduct findByid(Long id);

	@Query(value = "select b.productname,b.productcategory,c.categoryname,b.price,b.date,b.govt_price,b.id from buyproduct b INNER JOIN category c ON b.productcategory=c.id order by b.productname ASC", nativeQuery = true)
	List<Object[]> getbuyProductList();

}
