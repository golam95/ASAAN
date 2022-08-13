package com.biit.asaan.serviceImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biit.asaan.dto.ProductDTO;
import com.biit.asaan.model.SellingProduct;
import com.biit.asaan.repository.SellingProductRepository;
import com.biit.asaan.service.SellingProductService;

@Service
public class SellingProductServiceImpl implements SellingProductService {

	@Autowired
	private SellingProductRepository sellingproductRepo;

	@Override
	public void save(SellingProduct addSellinging) {
		addSellinging.setDate(new Date());
		sellingproductRepo.save(addSellinging);
	}

	@Override
	public List<SellingProduct> getList() {
		return sellingproductRepo.findAll();
	}

	@Override
	public SellingProduct findByproductName(String productName) {
		return sellingproductRepo.findByproductName(productName);
	}

	@Override
	public List<ProductDTO> getSellProductList() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		for (Object[] obj : sellingproductRepo.getSellProductList()) {
			ProductDTO productdto = new ProductDTO();
			productdto.setProductName((String) obj[0]);
			productdto.setProductCategory((String) obj[1]);
			productdto.setCategoryName((String) obj[2]);
			productdto.setPrice((double) obj[3]);
			productdto.setDate((Date) obj[4]);
			productdto.setGovtprice((double) obj[5]);
			productdto.setProductId((BigInteger) obj[6]);
			list.add(productdto);
		}
		return list;
	}

	@Override
	public Long count() {
		return sellingproductRepo.count();
	}

	@Override
	public void deleteSellingProduct(Long id) {
		sellingproductRepo.deleteById(id);
	}

}
