package com.biit.asaan.dto;

import java.math.BigInteger;
import java.util.Date;
import lombok.Data;

@Data
public class ProductDTO {
	private String productName;
	private String productCategory;
	private String categoryName;
	private double price;
	private double govtprice;
	private Date date;
	private BigInteger productId;
}
