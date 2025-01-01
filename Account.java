package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Account {

	@Id
	private int id;
	private String name;
	private double balance;
	
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Account(int id, String name, double balance) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
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


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
