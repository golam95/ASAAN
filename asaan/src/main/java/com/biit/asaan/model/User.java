package com.biit.asaan.model;

import javax.persistence.*;
import lombok.Data;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String userName;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "password")
	private String password;

	@Column(name = "can_add")
	private String canAdd;

	@Column(name = "can_update")
	private String canUpdate;

	@Column(name = "can_delete")
	private String canDelete;

	@Column(name = "block")
	private String blockUser;

	@Column(name = "rolename")
	private String rolename;

	@Column(name = "country")
	private String countryName;

	@Column(name = "createDate")
	private Date date;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

}
