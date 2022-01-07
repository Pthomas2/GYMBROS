package com.philip.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gymMembers")
@Entity
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Role {
		ROLE_USER, ROLE_ADMIN
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Mbr_Id")
	private Long mbrId;
	
	@Column(name = "Mbr_Fname")
	private String fname;
	
	@Column(name = "Mbr_Lname")
	private String lname;
	
	@Column(name = "Mbr_Email")
	private String email;

	@Column(name = "Mbr_IsAdmin", columnDefinition = "boolean default false")
	private Boolean isAdmin;

	@Column(name = "Mbr_Gym", unique = false, nullable = true)
	private Long gymId;

	@Column(name = "Mbr_Username", unique = true, nullable = false)
	private String username;

	@Column(name = "Mbr_Password", nullable = false)
	private String password;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(Long mbrId, String fname, String lname, String email, Boolean isAdmin, Long gymId,
			String username, String password) {
		super();
		this.mbrId = mbrId;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.isAdmin = isAdmin;
		this.gymId = gymId;
		this.username = username;
		this.password = password;
	}

	public Long getMbrId() {
		return mbrId;
	}

	public void setMbrId(Long mbrId) {
		this.mbrId = mbrId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Long getGymId() {
		return gymId;
	}

	public void setGymId(Long gymId) {
		this.gymId = gymId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	


}