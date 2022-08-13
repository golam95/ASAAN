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
@Table(name = "farmerschedule")
public class FarmerSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private Long userId;

	private String name;

	private String email;

	private String productName;

	private String SchudleDate;

	private String ScheduleTime;

	private String division;

	private String status;

	private Date date;

}
