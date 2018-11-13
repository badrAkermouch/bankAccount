package fr.socegen.bankAccount;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import fr.socegen.bankAccount.exception.AccountException;
import fr.socegen.bankAccount.factory.CustomerFactory;
import fr.socegen.bankAccount.model.Account;
import fr.socegen.bankAccount.model.Customer;
import fr.socegen.bankAccount.service.AccountService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Specific test class for deposit */
public class DepositTest {

	private AccountService accountService;
	private Customer customer;
	private Account account;

	public DepositTest() {
		accountService = new AccountService();

	}

	/**
	 * For each test we instantiate a new test customer object that includes an
	 * account object
	 */
	@Before
	public void init() {
		customer = CustomerFactory.getCustomerA();
		account = customer.getAccount();
	}

	/**
	 * test for a deposit of 100 euros (normal case)
	 * 
	 * @throws AccountException
	 */
	@Test
	public void test_deposit_100() throws AccountException {
		// Case 1 : make a deposit in my account
		// deposit of 100
		accountService.deposit(account, BigDecimal.valueOf(100));
		assertEquals(BigDecimal.valueOf(100), account.getBalance(), "The amount does not match the account balance");
	}

	/*** test of deposit of null account */
	@Test
	public void test_deposit_null_account() throws AccountException {
		// Case 1 : make a deposit in my account
		// Deposit of null value
		Throwable e = Assertions.assertThrows(AccountException.class,
				() -> accountService.deposit(null, BigDecimal.TEN));
		assertTrue(e instanceof AccountException);
		AccountException accountException = (AccountException) e;
		assertEquals("Null account for deposit", accountException.getMessage());
	}

	/** test of deposit of null amount */
	@Test
	public void test_deposit_null_amount() throws AccountException {
		// Case 1 : make a deposit in my account
		// Deposit of null value
		Throwable e = Assertions.assertThrows(AccountException.class, () -> accountService.deposit(account, null));
		assertTrue(e instanceof AccountException);
		AccountException accountException = (AccountException) e;
		assertEquals(null, accountException.getOperationValue());
		assertEquals("Null value for deposit", accountException.getMessage());
	}

	/** test of deposit of negative amount value */
	@Test
	public void test_deposit_negative_amount() throws AccountException {
		// Case 1 : make a deposit in my account
		// Deposit of null value
		Throwable e = Assertions.assertThrows(AccountException.class,
				() -> accountService.deposit(account, BigDecimal.valueOf(-10)));
		assertTrue(e instanceof AccountException);
		AccountException accountException = (AccountException) e;
		assertEquals(BigDecimal.valueOf(-10), accountException.getOperationValue());
		assertEquals("Zero or négative value for deposit", accountException.getMessage());
	}
}
