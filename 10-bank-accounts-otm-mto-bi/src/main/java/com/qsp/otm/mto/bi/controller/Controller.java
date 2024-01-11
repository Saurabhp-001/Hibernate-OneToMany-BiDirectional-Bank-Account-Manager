package com.qsp.otm.mto.bi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.qsp.otm.mto.bi.model.Account;
import com.qsp.otm.mto.bi.model.Bank;

public class Controller {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgadmin");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	Bank bank = new Bank();
	Account account = new Account();

//	1. Add Bank
	public boolean addBank(Bank bank, ArrayList<Account> accounts) {

		if (bank != null) {  

			entityTransaction.begin();
			if (accounts != null) {
				
				for (Account account : accounts) {
					entityManager.persist(account);
				}
			}
			entityManager.persist(bank);
			entityTransaction.commit();
			return true;

		}
		return false;
	}

//	2.Add Account To Existing Bank
	public boolean addAccounts(Bank bank, List<Account> accounts) {

//		Query query = entityManager.createQuery("SELECT * FROM bank");

		if (bank != null) {

			entityTransaction.begin();

			for (Account account : accounts) {
				entityManager.persist(account);
			}

//			Update Bank with Accounts
			entityManager.merge(bank);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

//	3.Find Bank
	public Bank findBank(int bankId) {
//		returns bank
		return entityManager.find(Bank.class, bankId);	
	}
	
	public Account findAccount(int accountId) {
		
		return entityManager.find(Account.class, accountId);
	}

//	4.Update Bank And Account
	public boolean updateBank(int bankId, double newBalance) {

		Bank foundBank = findBank(bankId);
		if (foundBank != null) {

			entityTransaction.begin();
			entityManager.merge(foundBank);
			entityTransaction.commit();
			return true;
		} else {
			return false;
		}

	}

//	5.Remove Bank
	public boolean removeBank(int bankIdToRemove) {

		Bank bankToRemove = entityManager.find(Bank.class, bankIdToRemove);
		if (bankToRemove != null) {

			entityTransaction.begin();
//			List<Account> accountsToRemove = bankToRemove.getAccounts();
//			if (!accountsToRemove.isEmpty()) {
//
//				for (Account account : accountsToRemove) {
////					Delete Account From Relation First
//					bankToRemove.getAccounts().remove(accountsToRemove);
////					Then Delete Account From Bank
//					entityManager.remove(account);
//				}
//			}
			entityManager.remove(bankToRemove);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

//	6.Remove Account From Bank
	public boolean removeAccount(int bankIdForRemove, int accountIdToRemove) {

		Bank bankForRemove = findBank(bankIdForRemove);
		if (bankForRemove != null) {
			List<Account> accounts = bankForRemove.getAccounts();
			if (accounts.isEmpty()) {
				return false;
			}
			Account accountToRemove = null;
			for (Account account : accounts) {
				if (account.getAccountId() == accountIdToRemove) {
					accountToRemove = account;
					break;
				}
			}
			if (accountToRemove != null) {
				entityTransaction.begin();
				accounts.remove(accountToRemove);
				entityTransaction.commit();
				entityTransaction.begin();
				entityManager.remove(accountToRemove);
				entityTransaction.commit();
				return true;
			}
			return false;
		}
		return false;
	}

}
