package com.mss.bankmngmtsys.model;

import java.math.BigDecimal;

/**
 * Account Class
 * @author RNF
 */
public class Account {
    private Long accId;
    private Long accountOwnerId;
    private String accountNumber;
    private BigDecimal balance = BigDecimal.ZERO;

    public Account(Long accId, Long accountOwnerId, String accountNumber) {
        this.accId = accId;
        this.accountOwnerId = accountOwnerId;
        this.accountNumber = accountNumber;
    }

    public Long getAccId() {
        return accId;
    }

    public Long getAccountOwnerId() {
        return accountOwnerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public BigDecimal addToBalance(BigDecimal balance) {
        return this.balance = this.balance.add(balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accId=" + accId +
                ", accountOwner=" + accountOwnerId +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }

}
