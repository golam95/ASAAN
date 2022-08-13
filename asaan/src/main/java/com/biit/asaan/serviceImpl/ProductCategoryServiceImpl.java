package com.biit.asaan.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biit.asaan.model.ProdutCategory;
import com.biit.asaan.repository.ProductCategoryRepository;
import com.biit.asaan.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategroy;

	@Override
	public ProdutCategory findBycategoryName(String categoryName) {
		return productCategroy.findBycategoryName(categoryName);
	}

	@Override
	public void addCategory(ProdutCategory addcategory) {
		productCategroy.save(addcategory);
	}

	@Override
	public List<ProdutCategory> getList() {
		return productCategroy.findAll();
	}

	@Override
	public void deleteProductCategory(Long id) {
		productCategroy.deleteById(id);
	}
}
