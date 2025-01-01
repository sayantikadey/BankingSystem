package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.detailsobject.Accountdto;

import com.example.demo.service.service.Accountservice;

@RestController
@RequestMapping("/axisbank")
public class AccountController {
	@Autowired
	Accountservice accountservice;
	
	@PostMapping("/save")
	public ResponseEntity<Accountdto> addaccount(@RequestBody Accountdto accountdto)
	{
		return new ResponseEntity<Accountdto>(accountservice.createaccount(accountdto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Accountdto> getAccountByid(@PathVariable int id) 
	{
		Accountdto accountdto = accountservice.getAccountByid(id);
		return ResponseEntity.ok(accountdto);
	}
	
	@GetMapping("/get/{name}")
	public ResponseEntity<Accountdto> getAccountByname(@PathVariable String name) throws AccountNotFoundException 
	{
		Accountdto accountdto = accountservice.getAccountByname(name);
		return ResponseEntity.ok(accountdto);
	}
	

	@PutMapping("/{id}/deposit")
	public ResponseEntity<Accountdto> deposit(@PathVariable int id, @RequestBody Map<String, Double> request) {
		Double amount = request.get("amount");

		Accountdto accountDto = accountservice.deposit(id, amount);

		return ResponseEntity.ok(accountDto);

	}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<Accountdto> withdraw(@PathVariable int id, @RequestBody Map<String, Double> request) {
		Double amount = request.get("amount");
		Accountdto accountDto = accountservice.withdraw(id, amount);

		return (ResponseEntity<Accountdto>) ResponseEntity.ok(accountDto);

	}

	@GetMapping
	public ResponseEntity<List<Accountdto>> GetAllAccounts() {
		List<Accountdto> acconuts = accountservice.getAll();

		return ResponseEntity.ok(acconuts);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable int id) {
      accountservice.deleteAccount(id);
      return ResponseEntity.ok("Account deleted");
	}
}
