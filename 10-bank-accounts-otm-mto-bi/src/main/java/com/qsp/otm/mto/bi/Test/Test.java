package com.qsp.otm.mto.bi.Test;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.qsp.otm.mto.bi.model.Account;
import com.qsp.otm.mto.bi.model.Bank;

public class Test {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgadmin");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Scanner sc = new Scanner(System.in);
		Bank bank = new Bank();
		Account account = new Account();
		
		System.out.println("1.Add Account \n2.Find By Bank \n3.Find By Account \n");
		System.out.println("Enter Choice");
		int userChoice = sc.nextInt();
		sc.nextLine();
		
		switch (userChoice) {
		
		case 1:
			
			bank.setBankId(1);
			bank.setBankName("SBI");
			bank.setBankLoc("Mumbai");
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;

		default:
			System.out.println("Invalid Choice !");
			break;
		}
	}

}
