package com.containersolutions.api.models;

import java.io.Serializable;

public class PeopleDTO implements Serializable {

	private static final long serialVersionUID = -989291609007896419L;

	private String uuid;

	private boolean survived;

	private int passengerClass;

	private String sex;
	
	private String name;

	private double age;

	private int siblingsOrSpousesAboard;

	private int parentsOrChildrenAboard;

	private double fare;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isSurvived() {
		return survived;
	}

	public void setSurvived(boolean survived) {
		this.survived = survived;
	}

	public int getPassengerClass() {
		return passengerClass;
	}

	public void setPassengerClass(int passengerClass) {
		this.passengerClass = passengerClass;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public int getSiblingsOrSpousesAboard() {
		return siblingsOrSpousesAboard;
	}

	public void setSiblingsOrSpousesAboard(int siblingsOrSpousesAboard) {
		this.siblingsOrSpousesAboard = siblingsOrSpousesAboard;
	}

	public int getParentsOrChildrenAboard() {
		return parentsOrChildrenAboard;
	}

	public void setParentsOrChildrenAboard(int parentsOrChildrenAboard) {
		this.parentsOrChildrenAboard = parentsOrChildrenAboard;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "PeopleDTO [uuid=" + uuid + ", survived=" + survived + ", passengerClass=" + passengerClass + ", sex="
				+ sex + ", age=" + age + ", siblingsOrSpousesAboard=" + siblingsOrSpousesAboard
				+ ", parentsOrChildrenAboard=" + parentsOrChildrenAboard + ", fare=" + fare + "]";
	}
}
