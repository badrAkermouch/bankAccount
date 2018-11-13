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

/** Specific class for history of withdrawal tests */
public class WithdrawalTest {

	private AccountService accountService;
	private Customer customer;
	private Account account;

	public WithdrawalTest() {
		accountService = new AccountService();
	}

	@Before
	public void doDeposit() throws AccountException {
		customer = CustomerFactory.getCustomerA();
		account = customer.getAccount();
		accountService.deposit(account, BigDecimal.valueOf(100));
	}

	/**
	 * test of withdrawal of 10 euros (normal case)
	 * 
	 * @throws AccountException
	 */
	@Test
	public void test_withdrawal_10() throws AccountException {
		// Case 1 : make a deposit in my account
		// deposit of 100
		doDeposit();
		accountService.withdrawal(account, BigDecimal.TEN);
		assertEquals(BigDecimal.valueOf(90), account.getBalance(), "The amount does not match the account balance");
	}

	/**
	 * test of withdrawal with insufficient balance
	 * 
	 * @throws AccountException
	 */
	@Test
	public void test_withdrawal_insufficient_balance() throws AccountException {
		// Case 1 : make a deposit in my account
		// deposit of 100
		doDeposit();
		Throwable e = Assertions.assertThrows(AccountException.class,
				() -> accountService.withdrawal(account, BigDecimal.valueOf(300)));
		assertEquals("insufficient balance", e.getMessage());
		assertTrue(e instanceof AccountException);
	}

	/** test of withdrawal with null account */
	@Test
	public void test_withdrawal_null_account() throws AccountException {
		Throwable e = Assertions.assertThrows(AccountException.class,
				() -> accountService.withdrawal(null, BigDecimal.TEN));
		assertEquals("Null account for withdrawal", e.getMessage());
		assertTrue(e instanceof AccountException);
	}

	/** test of withdrawal with null amount */
	@Test
	public void test_withdrawal_null_amount() throws AccountException {
		Throwable e = Assertions.assertThrows(AccountException.class, () -> accountService.withdrawal(account, null));
		assertTrue(e instanceof AccountException);
		AccountException accountException = (AccountException) e;
		assertEquals(null, accountException.getOperationValue());
		assertEquals("Null value for withdrawal", accountException.getMessage());
	}

	/** test of withdrawal with negative amount */
	@Test
	public void test__withdrawal_negative_amount() throws AccountException {
		Throwable e = Assertions.assertThrows(AccountException.class,
				() -> accountService.withdrawal(account, BigDecimal.valueOf(-10)));
		assertTrue(e instanceof AccountException);
		AccountException accountException = (AccountException) e;
		assertEquals(BigDecimal.valueOf(-10), accountException.getOperationValue());
		assertEquals("Zero or négative value for withdrawal", accountException.getMessage());
	}
}
