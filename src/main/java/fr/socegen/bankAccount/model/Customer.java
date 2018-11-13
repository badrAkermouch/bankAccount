package fr.socegen.bankAccount.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class Customer {
	/**
	 * Static variable user to generate a unique id for each user - AtomicLong
	 * used here to unsure thread safe
	 */
	private static AtomicLong generator = new AtomicLong(0);

	private long id;
	private String firstName;
	private String lastName;
	private LocalDate birthday;
	private String function;

	/**
	 * To facilitate this implementation i supposed that each customer has ont
	 * account but in the real life a customer may have many accounts
	 */
	private Account account;

	public Customer() {
		super();
		this.id = generator.incrementAndGet();
	}

	public Customer(String firstName, String lastName, LocalDate birthday, String function, Account account) {
		super();
		this.id = generator.incrementAndGet();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.function = function;
		this.account = account;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
