package fr.socegen.bankAccount;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.socegen.bankAccount.exception.AccountException;
import fr.socegen.bankAccount.factory.CustomerFactory;
import fr.socegen.bankAccount.model.Account;
import fr.socegen.bankAccount.model.Customer;
import fr.socegen.bankAccount.service.AccountService;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Specific class for history of operations tests */
public class OperationTest {

	/** To compare with the system ouput purpose */
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	private AccountService accountService;
	private Customer customer;
	private Account account;

	public OperationTest() {
		accountService = new AccountService();
		customer = CustomerFactory.getCustomerA();
		account = customer.getAccount();
	}

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	/** test of history of empty operations */
	@Test
	public void print_my_operations_empty_operations() {
		accountService.printOperations(account);
		assertEquals("no operation is available for the account", outContent.toString().trim());

	}

	/** test of history of 2 operations (debit and credit) */
	@Test
	public void print_my_operation_deposit_100_withdrawal_10() throws AccountException {
		accountService.deposit(account, new BigDecimal(100));
		accountService.withdrawal(account, BigDecimal.TEN);
		accountService.printOperations(account);
		String expectedOutput = "Operation [operationName=Deposit of 100 €, operationDate=2018-11-13, operationType=Credit, operationAmount=100]";
		expectedOutput += System.getProperty("line.separator");
		expectedOutput += "Operation [operationName=withdrawal of 10 €, operationDate=2018-11-13, operationType=Debit, operationAmount=10]";
		assertEquals(expectedOutput, outContent.toString().trim());

	}

}
