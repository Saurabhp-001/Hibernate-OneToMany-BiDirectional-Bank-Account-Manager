package com.qsp.otm.mto.bi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bank {
	@Id
	private int bankId;
	private String bankName;
	private String bankLoc;
	@OneToMany
	private List<Account> accounts;

	public Bank() {
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankLoc() {
		return bankLoc;
	}

	public void setBankLoc(String bankLoc) {
		this.bankLoc = bankLoc;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
