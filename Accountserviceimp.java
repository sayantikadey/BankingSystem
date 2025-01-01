package com.example.demo.service.serviceimp;

import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.detailsobject.Accountdto;
import com.example.demo.entity.Account;
import com.example.demo.mapper.Accountmapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.service.Accountservice;

@Component
public class Accountserviceimp  implements Accountservice {
	
	@Autowired
	AccountRepository accountrepository;

	@Override
	public Accountdto createaccount(Accountdto accountdto) {
		Account acc=Accountmapper.maptoaccount(accountdto);
		Account accountsaved=accountrepository.save(acc);
		return Accountmapper.maptoaccountdto(accountsaved);
	}

	@Override
	public Accountdto getAccountByid(int id) {
		Account account=accountrepository.findById(id).orElseThrow(()->new  RuntimeException("id does not exists"));
		return Accountmapper.maptoaccountdto(account);
	}
	

	
	@Override
	public Accountdto deposit(int id, double amount) {

		Account account=accountrepository.findById(id).orElseThrow(()->new  RuntimeException("id does not exists"));
		double	balance=account.getBalance();
		balance=balance+amount;
		account.setBalance(balance);
		Account savedaccount=accountrepository.save(account);
		return Accountmapper.maptoaccountdto(savedaccount);
	}

	@Override
	public Accountdto withdraw(int id, double amount) {
		Account account=accountrepository.findById(id).orElseThrow(()->new  RuntimeException("id does not exists"));
		if (account.getBalance()<amount) {
			throw new RuntimeException("infsuffient balance");
		}

		double total=account.getBalance()-amount;

		account.setBalance(total);
		Account savedacAccount=accountrepository.save(account);

		return Accountmapper.maptoaccountdto(savedacAccount);
	}
	

	@Override
	public List<Accountdto> getAll() {
		List<Account> accounts=accountrepository.findAll();
		return (List<Accountdto>) accounts.stream().map((account)->Accountmapper.maptoaccountdto(account)).collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(int id) {
		Account account=accountrepository.findById(id).orElseThrow(()->new RuntimeException("id does nt exixst"));
		accountrepository.deleteById(id);
		
	}

	@Override
	public Accountdto getAccountByname(String name) throws AccountNotFoundException {
		Account account=accountrepository.findByName(name);
		if(account==null) {
			throw new AccountNotFoundException("name not found");
		}
		return Accountmapper.maptoaccountdto(account);
	}

	
}
