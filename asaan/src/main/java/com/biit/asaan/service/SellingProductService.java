package com.biit.asaan.service;

import java.util.List;
import com.biit.asaan.dto.ProductDTO;
import com.biit.asaan.model.SellingProduct;

public interface SellingProductService {

	void save(SellingProduct addSellinging);

	List<SellingProduct> getList();

	SellingProduct findByproductName(String productName);
	
	List<ProductDTO> getSellProductList();
	
	Long count();
	
	void deleteSellingProduct(Long id);
}
