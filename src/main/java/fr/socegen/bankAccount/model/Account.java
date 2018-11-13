package fr.socegen.bankAccount.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
	private String accountNumber;
	private BigDecimal balance = BigDecimal.ZERO;
	private LocalDate creationDate;
	private boolean status;

	/** Each account has a list of operations (one to many) */
	private List<Operation> operations = new ArrayList<Operation>();

	public Account() {
		super();
	}

	public Account(String accountNumber, BigDecimal balance, LocalDate creationDate, boolean status) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.creationDate = creationDate;
		this.status = status;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public void addOperation(Operation operation) {
		operations.add(operation);
	}

}
