package com.biit.asaan.service;

import java.util.List;
import com.biit.asaan.model.ProdutCategory;

public interface ProductCategoryService {
	
	ProdutCategory findBycategoryName(String categoryName);
	
	void addCategory(ProdutCategory addcategory);
	
	List<ProdutCategory> getList();
	
	void deleteProductCategory(Long id);

}
