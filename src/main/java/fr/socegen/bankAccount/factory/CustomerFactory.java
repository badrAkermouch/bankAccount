package fr.socegen.bankAccount.factory;

import java.math.BigDecimal;
import java.time.LocalDate;

import fr.socegen.bankAccount.model.Account;
import fr.socegen.bankAccount.model.Customer;

/** Factory layer for instantiation of test customers */
public class CustomerFactory {

	public static Customer getCustomerA() {
		Account account = new Account("1111111111", BigDecimal.ZERO, LocalDate.parse("1990-08-08"), true);
		Customer customer = new Customer("Tata", "Toto", LocalDate.parse("2016-03-05"), null, account);
		return customer;

	}

}
