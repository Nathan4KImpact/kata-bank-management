package com.mss.bankmgnt.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 * Account Class
 * @author RNF
 */
public class Account implements  Cloneable {

    private transient NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);

    private Long accId;
    private Long accountClientId;
    private String accountNumber;
    private BigDecimal balance;
    private String balancetxt;

    public Account(Long accId, Long accountClientId, String accountNumber) {
        this.accId = accId;
        this.accountClientId = accountClientId;
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;  //calculated
        this.balancetxt = currencyFormat.format(balance);
    }

    public Long getAccId() {
        return accId;
    }

    public Long getAccountClientId() {
        return accountClientId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void addToBalance(BigDecimal balance) {
        this.balance = this.balance.add(balance);
        this.balancetxt = currencyFormat.format(balance);
       // return balance;
    }

    public String getBalancetxt() {
        return balancetxt;
    }

    public void setBalancetxt(String balancetxt) {
        this.balancetxt = balancetxt;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accId=" + accId +
                ", accountOwner=" + accountClientId +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balancetxt +
                '}';
    }

    @Override
    public Account clone() {
        Account acc = null;
        try {
            acc = (Account) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return acc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return accId.equals(account.accId) && accountClientId.equals(account.accountClientId) && accountNumber.equals(account.accountNumber) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accId, accountClientId, accountNumber, balance);
    }
}
