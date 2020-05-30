package com.ntt.model;

import java.sql.Date;

public class Employee {
	private int id;
	private String employeename;
	private String address;
	private String dateofjoining;
	private int experience;
	private String dateofbirth;
	
	
	public Employee(int id, String employeename, String address, String dateofjoining, int experience,
			String dateofbirth) {
		super();
		this.id = id;
		this.employeename = employeename;
		this.address = address;
		this.dateofjoining = dateofjoining;
		this.experience = experience;
		this.dateofbirth = dateofbirth;
	}
	
	
	public Employee(String employeename, String address, String dateofjoining, int experience, String dateofbirth) {
		super();
		this.employeename = employeename;
		this.address = address;
		this.dateofjoining = dateofjoining;
		this.experience = experience;
		this.dateofbirth = dateofbirth;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDateofjoining() {
		return dateofjoining;
	}
	public void setDateofjoining(String dateofjoining) {
		this.dateofjoining = dateofjoining;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	

}
