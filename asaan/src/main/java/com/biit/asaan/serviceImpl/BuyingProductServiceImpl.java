package com.biit.asaan.serviceImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biit.asaan.dto.ProductDTO;
import com.biit.asaan.model.BuyingProduct;
import com.biit.asaan.repository.BuyingProductRepository;
import com.biit.asaan.service.BuyingProductService;

@Service
public class BuyingProductServiceImpl implements BuyingProductService {

	@Autowired
	private BuyingProductRepository buyingproductRepo;

	@Override
	public void save(BuyingProduct addBuying) {
		addBuying.setDate(new Date());
		buyingproductRepo.save(addBuying);
	}

	@Override
	public List<BuyingProduct> getList() {
		return buyingproductRepo.findAll();
	}

	@Override
	public BuyingProduct findByproductName(String productName) {
		return buyingproductRepo.findByproductName(productName);
	}

	@Override
	public List<ProductDTO> getbuyProductList() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		for (Object[] obj : buyingproductRepo.getbuyProductList()) {
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
		return buyingproductRepo.count();
	}

	@Override
	public void deleteBuyProduct(Long id) {
		buyingproductRepo.deleteById(id);	
	}

}
