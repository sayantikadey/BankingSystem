package com.example.demo.service.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Service;

import com.example.demo.detailsobject.Accountdto;


@Service
public interface Accountservice {

	
	Accountdto createaccount(Accountdto accountdto);

	Accountdto getAccountByid(int id);
	
	Accountdto getAccountByname(String name) throws AccountNotFoundException;
	
	Accountdto deposit(int id,double amount);
	
	Accountdto withdraw(int id ,double amount);
	
	List<Accountdto> getAll();
	
	void deleteAccount(int id);
}
