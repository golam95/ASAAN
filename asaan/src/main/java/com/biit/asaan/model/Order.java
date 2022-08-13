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
@Table(name = "cusorder")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private Long userId;

	private String userName;

	private String productName;

	private double productPrice;

	private int quantity;

	private String country;

	private String town;

	private String zipcode;

	private String shipingAddress;

	private String status;

	private Date date;
	

}
