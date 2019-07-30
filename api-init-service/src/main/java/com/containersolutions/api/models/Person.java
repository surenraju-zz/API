package com.containersolutions.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "people")
public class Person {

	@Id
	@Column(nullable = false)
	private String uuid;

	@Column
	private boolean survived;

	@Column
	private int passengerClass;

	@Column
	private String name;

	@Column(length = 6)
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@Column
	private int age;

	@Column
	private int siblingsOrSpousesAboard;

	@Column
	private int parentsOrChildrenAboard;

	@Column
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

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
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
}
