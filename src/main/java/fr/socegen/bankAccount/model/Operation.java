package fr.socegen.bankAccount.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Operation {
	private long operationId;
	private String operationName;
	private LocalDate operationDate;
	private String operationType;
	private BigDecimal operationAmount;

	public long getOperationId() {
		return operationId;
	}

	public void setOperationId(long operationId) {
		this.operationId = operationId;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public LocalDate getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(LocalDate operationDate) {
		this.operationDate = operationDate;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getOperationAmount() {
		return operationAmount;
	}

	public void setOperationAmount(BigDecimal operationAmount) {
		this.operationAmount = operationAmount;
	}

	@Override
	public String toString() {
		return "Operation [operationName="
				+ operationName + ", operationDate=" + operationDate
				+ ", operationType=" + operationType + ", operationAmount="
				+ operationAmount + "]";
	}

}
