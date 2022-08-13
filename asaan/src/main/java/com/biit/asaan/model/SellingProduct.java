package com.biit.asaan.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sellproduct")
public class SellingProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "productname")
	private String productName;

	@Column(name = "productcategory")
	private String productCategory;

	@Column(name = "price")
	private double setprice;

	@Column(name = "govtPrice")
	private double govtPrice;

	@Column(name = "date")
	private Date date;

	@Column(name = "quantity")
	private Integer quantity;

}
