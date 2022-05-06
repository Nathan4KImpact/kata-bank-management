package com.mss.bankmngmtsys.model;

import java.math.BigDecimal;

/**
 * Account Class
 * @author RNF
 */
public class Account {
    private Long accId;
    private Client accountOwner;
    private String accountNumber;
    private BigDecimal balance;

    public Long getAccId() {
        return accId;
    }

    public Client getAccountOwner() {
        return accountOwner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setAccountOwner(Client accountOwner) {
        this.accountOwner = accountOwner;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accId=" + accId +
                ", accountOwner=" + accountOwner.getCliName() +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
