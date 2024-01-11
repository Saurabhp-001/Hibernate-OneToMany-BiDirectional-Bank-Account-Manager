package com.qsp.otm.mto.bi.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.qsp.otm.mto.bi.controller.Controller;
import com.qsp.otm.mto.bi.model.Account;
import com.qsp.otm.mto.bi.model.Bank;

public class View {

	static Bank bank = new Bank();
	static Account account = new Account();
	static ArrayList<Account> accounts = new ArrayList<Account>();
	static Scanner sc = new Scanner(System.in);
	static Controller controller = new Controller();

	public static void main(String[] args) {

		do {

			System.out.println("\n$$$$$$$$$$$ Bank - Accounts $$$$$$$$$$");
			System.out.println("======================================");
			System.out.println("| 1. Insert Bank And Account Data     |");
			System.out.println("| 2. Insert Accounts To Existing Bank |");
			System.out.println(" ------------------------------------ ");
			System.out.println("| 3. Fetch Bank And Accounts Data     |");
			System.out.println("| 4. Fetch Perticular Account         |");
			System.out.println(" ------------------------------------ ");
			System.out.println("| 5.Update Bank Data				 |");
			System.out.println("| 6.Update Account Data              |");
			System.out.println(" ------------------------------------ ");
			System.out.println("| 7.Remove Bank Data                 |");
			System.out.println("| 8.Remove Accounts From Bank        |");
			System.out.println(" ------------------------------------ ");
			System.out.println("| 0.nExit                            |");
			System.out.println("======================================");
			System.out.print("\nEnter Number To Select Option : ");
			int userChoice = sc.nextInt();
			sc.nextLine();

//			Switch Case to Perform Operations
			switch (userChoice) {
//		1.Insert Bank and Account Data
			case 1:

//				Bank Details
				Bank bankToAdd = new Bank();
				System.out.println("\n$$$ Enter Bank Details $$$");
				System.out.print("\nEnter Bank Id : ");
				bankToAdd.setBankId(sc.nextInt());
				sc.nextLine();
				System.out.print("Enter Bank Name : ");
				bankToAdd.setBankName(sc.nextLine());
				System.out.print("Enter Bank Location : ");
				bankToAdd.setBankLoc(sc.nextLine());

//				Add Account?
				System.out.println("\nWant To Add Account In This Bank ?");
				System.out.println("-------------------------------");
				System.out.println("1.Yes \n2.No");
				System.out.print("Enter Number To Select : ");
				int addAccountToBank = sc.nextInt();
				sc.nextLine();

				ArrayList<Account> accountsToAdd = null;
//				1.Add Accounts To Bank
				if (addAccountToBank == 1) {

//					Add Multiple Accounts Function
					accountsToAdd = addMultipleAccounts();
					bankToAdd.setAccounts(accountsToAdd);

				}
//				2.Add Null Accounts To Bank	
				else if (addAccountToBank == 2) {

					bankToAdd.setAccounts(accountsToAdd);
				}

//				Call addBank with bank and addAccounts
				if (controller.addBank(bankToAdd, accountsToAdd)) {
					System.out.println("\nData Inserted");
				} else {
					System.err.println("\nData Insertion Failed...!");
				}

				break;

//		2.Add Account To Existing Bank
			case 2:
//				Take Bank Id Input To Add More Accounts
				System.out.println("\n$$$ Add Accounts To Existing Bank $$$");
				System.out.print("\nEnter Bank Id To Add Account : ");
				int bankId = sc.nextInt();
				sc.nextLine();
				Bank foundBank = controller.findBank(bankId);

				if (foundBank != null) {

					ArrayList<Account> addAccountsToBank = null;

//					No Accounts In Bank
					if (foundBank.getAccounts().isEmpty()) {

//						Function For Adding Multiple Accounts
						addAccountsToBank = addMultipleAccounts();
//						set list of accounts to bank
						foundBank.setAccounts(addAccountsToBank);

					}
//					Bank Already Has Accounts
					else {
//						Accounts In Bank
						List<Account> accountsInBank = foundBank.getAccounts();
//						Accounts To Add In Bank
						addAccountsToBank = addMultipleAccounts();
//						Add new Accounts To Existing Accounts
						accountsInBank.addAll(addAccountsToBank);
						foundBank.setAccounts(accountsInBank);

					}

					if (controller.addAccounts(foundBank, addAccountsToBank)) {
						System.out.println("\nAccount Data Inserted...");
					} else {
						System.err.println("\nAccount Data Insertion Failed...!");
					}

				} else {
					System.err.println("\nBank Id - " + bankId + " Not Exists In Database...!");
				}

				break;
//		3.Find Bank And Account
			case 3:

				System.out.println("\n$$$ Fetch Bank And Accounts $$$");
				System.out.print("\nEnter Bank Id To Fetch : ");
				int bankIdToFind = sc.nextInt();
				sc.nextLine();
				Bank foundBank1 = controller.findBank(bankIdToFind);

				if (foundBank1 != null) {

					System.out.println("\n$$$ Bank Details $$$");
					System.out.println("\nBank Id -------> " + foundBank1.getBankId());
					System.out.println("Bank Name -------> " + foundBank1.getBankName());
					System.out.println("Bank Location ---> " + foundBank1.getBankLoc());
					System.out.println("----------------------");
					System.out.println("\n$$$ Accounts In " + foundBank1.getBankName() + " $$$");

					List<Account> foundAccounts = foundBank1.getAccounts();
					if (foundAccounts.isEmpty()) {

						System.err
								.println("No Account Exists In Bank Id - " + bankIdToFind + ", Please Add Accounts...");

					} else {

						for (Account account : foundAccounts) {

							System.out.println("\nAccount Id --------> " + account.getAccountId());
							System.out.println("Account Holder Name -> " + account.getAccountName());
							System.out.println("Account Balance -----> " + account.getBalance());
							System.out.println("-----------------------");
						}
					}

				} else {
					System.out.println("Bank Id - " + bankIdToFind + " Not Found !");
				}

				break;
//		4.Fetch Perticular Account
			case 4:

				System.out.println("\n$$$ Find Account In Bank $$$");
				System.out.print("\nEnter Account Id : ");
				int accountIdToFind = sc.nextInt();
				sc.nextLine();

				Account findAccount = controller.findAccount(accountIdToFind);
				if (findAccount != null) {

					System.out.println("\n$$$ Account Details $$$");
					System.out.println("\nAccount Id --------> " + findAccount.getAccountId());
					System.out.println("Account Name ------> " + findAccount.getAccountName());
					System.out.println("Account Balance ---> " + findAccount.getBalance());
					System.out.println("---------------------------");

					Bank bankOfAccount = findAccount.getBank();
					System.out.println("\n$$$ Bank Details Of Account - " + accountIdToFind + " $$$");
					System.out.println(bankOfAccount);
					System.out.println("\nBank Id ---------> " + bankOfAccount.getBankId());
					System.out.println("Bank Name -------> " + bankOfAccount.getBankName());
					System.out.println("Bank LocATION ---> " + bankOfAccount.getBankLoc());
					System.out.println("-----------------------------");
					
				} else {
					System.err.println("\nAccount Id - " + accountIdToFind + " Not Found !");
				}

				break;

//		5.Update Bank Data

			case 5:

				System.out.println("\n$$$ Update Bank - Account Details $$$");
				System.out.print("\nEnter Bank Id To Update : ");
				int bankIdForUpdate = sc.nextInt();
				sc.nextLine();

				Bank bankToUpdate = controller.findBank(bankIdForUpdate);

				if (bankToUpdate != null) {
					List<Account> accountsToUpdate = bankToUpdate.getAccounts();

					if (accountsToUpdate != null) {

						System.out.print("\nEnter Account Id To Update : ");
						int accountIdToUpdate = sc.nextInt();
						sc.nextLine();

						Account accountToUpdate = null;
						for (Account account : accountsToUpdate) {

							if (account.getAccountId() == accountIdToUpdate) {
								accountToUpdate = account;
							}

						}

						if (accountToUpdate != null) {

							System.out.print("Enter Balance To Update : ");
							double newBalance = sc.nextDouble();
							sc.nextInt();

							if (controller.updateBank(bankIdForUpdate, newBalance)) {
								System.out.println("Bank Data Updated...");
							} else {
								System.err.println("Bank Data Updation Failed...!");
							}

						} else {
							System.err.println("Accoount Id - " + accountIdToUpdate + " Not Found !");
						}

					} else {
						System.out.println("No Accounts In Bank !");
					}

				} else {
					System.out.println("done");
				}

				break;

//		6.Update Account Data
			case 6:

				break;

//		7.Remove Bank
			case 7:

				System.out.println("\n$$$ Delete Bank Details $$$");
				System.out.print("\nEnter Bank Id To Remove : ");
				int bankIdToRemove = sc.nextInt();
				sc.nextLine();

				if (controller.findBank(bankIdToRemove) != null) {

					if (controller.removeBank(bankIdToRemove)) {

						System.out.println("Bank Id - " + bankIdToRemove + " Removed...");
					} else {

						System.out.println("Bank Id - " + bankIdToRemove + " Not Removed...");
					}

				} else {
					System.err.println("Bank Id - " + bankIdToRemove + " Not Exists In Database...!");
				}

				break;
//		8.Remove Account From Bank
			case 8:

				System.out.println("\nEnter Bank Id : ");
				int bankIdForRemove = sc.nextInt();
				sc.nextLine();

				Bank bankToRemove = controller.findBank(bankIdForRemove);

				if (bankToRemove != null) {

					System.out.println("\nEnter Account Id : ");
					int accountIdToRemove = sc.nextInt();
					sc.nextLine();

					controller.removeAccount(bankIdForRemove, accountIdToRemove);

				} else {
					System.out.println("\nBank Id - " + bankIdForRemove + " Not Found...!");
				}

				break;
//			7.Exit
			case 0:
				System.out.println("$$$ THANK YOU $$$");
				sc.close();
				System.exit(0);
				break;

			default:
				System.out.println("Enter Valid Choice...!");
				break;
			}

		} while (true);
//		End Of Menu Driven Loop
	}

//	Add Multiple Accounts Function In View
	public static ArrayList<Account> addMultipleAccounts() {

		ArrayList<Account> accountsToAdd = new ArrayList<Account>();
//		Loop to Add Multiple Accounts
		boolean addMoreAccount = false;
		do {

//			Account Details	 
			Account accountToAdd = new Account();
			System.out.println("\n$$$ Add Account To Bank $$$");
			System.out.print("\nEnter Account Id : ");
			accountToAdd.setAccountId(sc.nextInt());
			sc.nextLine();
			System.out.print("Enter Account Holder Name : ");
			accountToAdd.setAccountName(sc.nextLine());
			System.out.print("Enter Account Balance : ");
			accountToAdd.setBalance(sc.nextDouble());
			sc.nextLine();

//			Add Account to List of Accounts
			accountsToAdd.add(accountToAdd);

//			Option to Add More Accounts
			System.out.println("\n1.Add More Accounts \n2.Done");
			System.out.println("--------------------------------");
			System.out.print("Enter Number To Select Option : ");
			int addAccount = sc.nextInt();
			sc.nextLine();

			if (addAccount == 1) {
				addMoreAccount = true;
			} else {
				addMoreAccount = false;
			}

		} while (addMoreAccount);
//		Returns List Of Accounts to Add in the Bank.
		return accountsToAdd;
	}

}
