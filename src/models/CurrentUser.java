package models;

import java.util.Date;

public class CurrentUser {

	int id;
	String name;
	String surname;
	String userName;
	String idNumber;
	Date dob;
	String email;
	int roleId;
	String roleName;
	int shopId;
	String shopName;
	String mondaySch;
	String tuesdaySch;
	String wednesdaySch;
	String thursdaySch;
	String fridaySch;
	String saturdaySch;
	String sundaySch;

	public CurrentUser(int id, String name, String surname, String idNumber, String userName, Date dob, String email,
			int roleId, String roleName, int shopId, String shopName, String mondaySch, String tuesdaySch,
			String wednesdaySch, String thursdaySch, String fridaySch, String saturdaySch, String sundaySch) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.userName = userName;
		this.idNumber = idNumber;
		this.dob = dob;
		this.email = email;
		this.roleId = roleId;
		this.roleName = roleName;
		this.shopId = shopId;
		this.shopName = shopName;
		this.mondaySch = mondaySch;
		this.tuesdaySch = tuesdaySch;
		this.wednesdaySch = wednesdaySch;
		this.thursdaySch = thursdaySch;
		this.fridaySch = fridaySch;
		this.saturdaySch = saturdaySch;
		this.sundaySch = sundaySch;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUserName() {
		return userName;
	}

	public String getMondaySch() {
		return mondaySch;
	}

	public void setMondaySch(String mondaySch) {
		this.mondaySch = mondaySch;
	}

	public String getTuesdaySch() {
		return tuesdaySch;
	}

	public void setTuesdaySch(String tuesdaySch) {
		this.tuesdaySch = tuesdaySch;
	}

	public String getWednesdaySch() {
		return wednesdaySch;
	}

	public void setWednesdaySch(String wednesdaySch) {
		this.wednesdaySch = wednesdaySch;
	}

	public String getThursdaySch() {
		return thursdaySch;
	}

	public void setThursdaySch(String thursdaySch) {
		this.thursdaySch = thursdaySch;
	}

	public String getFridaySch() {
		return fridaySch;
	}

	public void setFridaySch(String fridaySch) {
		this.fridaySch = fridaySch;
	}

	public String getSaturdaySch() {
		return saturdaySch;
	}

	public void setSaturdaySch(String saturdaySch) {
		this.saturdaySch = saturdaySch;
	}

	public String getSundaySch() {
		return sundaySch;
	}

	public void setSundaySch(String sundaySch) {
		this.sundaySch = sundaySch;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

}
