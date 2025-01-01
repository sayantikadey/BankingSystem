package com.example.demo.mapper;

import com.example.demo.detailsobject.Accountdto;
import com.example.demo.entity.Account;

public class Accountmapper {

	public static Account maptoaccount(Accountdto accountdto)
	{
		Account account=new Account(accountdto.getId(), accountdto.getName(), accountdto.getBalance());
		return account;
	}
	public static Accountdto maptoaccountdto(Account acc)
	{
		Accountdto actd=new Accountdto(acc.getId(), acc.getName(), acc.getBalance());
		return actd;
	}
}

