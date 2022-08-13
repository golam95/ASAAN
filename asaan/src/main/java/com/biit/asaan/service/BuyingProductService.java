package com.biit.asaan.service;

import java.util.List;
import com.biit.asaan.dto.ProductDTO;
import com.biit.asaan.model.BuyingProduct;

public interface BuyingProductService {
	
	void save(BuyingProduct addBuying);
	
	List<BuyingProduct> getList();

	BuyingProduct findByproductName(String productName);
	
	List<ProductDTO> getbuyProductList();
	
	Long count();
	
	void deleteBuyProduct(Long id);

}
