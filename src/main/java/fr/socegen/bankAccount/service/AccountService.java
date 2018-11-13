package fr.socegen.bankAccount.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import fr.socegen.bankAccount.exception.AccountException;
import fr.socegen.bankAccount.model.Account;
import fr.socegen.bankAccount.model.Operation;

/** The service layer for account operations **/
public class AccountService {

	/** The function make a deposit in an account */
	public void deposit(Account account, BigDecimal depositAmount) throws AccountException {
		globalPreChecks(account, depositAmount, "deposit");
		synchronized (account) {
			Operation operation = new Operation();
			operation.setOperationName("Deposit of " + depositAmount + " €");
			operation.setOperationDate(LocalDate.now());
			operation.setOperationType("Credit");
			operation.setOperationAmount(depositAmount);
			account.addOperation(operation);
			account.setBalance(account.getBalance().add(depositAmount));
		}
	}

	/** make a withdrawal from an account */
	public void withdrawal(Account account, BigDecimal withdrawalAmount) throws AccountException {
		globalPreChecks(account, withdrawalAmount, "withdrawal");
		synchronized (account) {
			if (account.getBalance().subtract(withdrawalAmount).compareTo(BigDecimal.ZERO) < 0) {
				throw new AccountException("insufficient balance");
			}
			Operation operation = new Operation();
			operation.setOperationName("withdrawal of " + withdrawalAmount + " €");
			operation.setOperationDate(LocalDate.now());
			operation.setOperationType("Debit");
			operation.setOperationAmount(withdrawalAmount);
			account.addOperation(operation);
			account.setBalance(account.getBalance().subtract(withdrawalAmount));
		}
	}

	/**
	 * see the history (operation, date, amount, balance) of operations for an
	 * account
	 */
	public void printOperations(Account account) {
		List<Operation> operations = account.getOperations();
		if (operations == null || operations.isEmpty()) {
			System.out.println("no operation is available for the account");
			return;
		}
		account.getOperations().stream().forEach(System.out::println);
	}

	/** Common controls for account operations */
	private synchronized void globalPreChecks(Account account, BigDecimal amount, String action)
			throws AccountException {
		if (account == null) {
			throw new AccountException("Null account for " + action);
		} else if (amount == null) {
			throw new AccountException("Null value for " + action);
		} else if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new AccountException("Zero or négative value for " + action, amount);
		}
	}

}
