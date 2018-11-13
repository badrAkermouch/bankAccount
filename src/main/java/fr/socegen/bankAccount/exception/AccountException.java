package fr.socegen.bankAccount.exception;

import java.math.BigDecimal;

/** Specific class for bank account exceptions **/
public class AccountException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3977317659700349469L;
	private BigDecimal operationValue;

	public AccountException(String message) {
		super(message);
	}

	public AccountException(String descrption, BigDecimal value) {
		super(descrption);
		this.operationValue = value;
	}

	public BigDecimal getOperationValue() {
		return operationValue;
	}

	public void setOperationValue(BigDecimal operationValue) {
		this.operationValue = operationValue;
	}

}
