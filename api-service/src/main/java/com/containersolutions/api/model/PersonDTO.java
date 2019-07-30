package com.containersolutions.api.model;

import java.io.Serializable;

public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean survived;

	private int passengerClass;

	private Sex sex;

	private String name;

	private int age;

	private int siblingsOrSpousesAboard;

	private int parentsOrChildrenAboard;

	private double fare;

	public PersonDTO() {

	}

	public PersonDTO(Person people) {
		this.survived = people.isSurvived();
		this.passengerClass = people.getPassengerClass();
		this.sex = people.getSex();
		this.name = people.getName();
		this.age = people.getAge();
		this.siblingsOrSpousesAboard = people.getSiblingsOrSpousesAboard();
		this.parentsOrChildrenAboard = people.getParentsOrChildrenAboard();
		this.fare = people.getFare();
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

	@Override
	public String toString() {
		return "People [survived=" + survived + ", passengerClass=" + passengerClass + ", sex=" + sex + ", age="
				+ age + ", siblingsOrSpousesAboard=" + siblingsOrSpousesAboard + ", parentsOrChildrenAboard="
				+ parentsOrChildrenAboard + ", fare=" + fare + "]";
	}
}
