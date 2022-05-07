package com.mss.bankmgnt.model;

import com.mss.bankmgnt.utils.EnumTransactionType;

import javax.swing.text.NumberFormatter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 * Transaction Class
 * @author RNF
 */
public class Transaction {
    private transient  DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE);
    private transient NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);

    private Long trxId;
    private transient BigDecimal amount;
    private String amountTxt;
    private EnumTransactionType trxType;
    private Account account;
    private String trxDate;


    public Transaction(Long trxId, BigDecimal amount, EnumTransactionType trxType, Account account) {
        this.trxId = trxId;
        this.amount = amount;
        this.amountTxt = currencyFormat.format(amount);
        this.trxType = trxType;
        this.account = account;
        this.trxDate = dateFormat.format(LocalDate.now());
    }

    public Long getTrxId() {
        return trxId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public EnumTransactionType getTrxType() {
        return trxType;
    }

    public void setTrxType(EnumTransactionType trxType) {
        this.trxType = trxType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(LocalDate trxDate) {
        this.trxDate = dateFormat.format(trxDate);
    }

    public String getAmountTxt() {
        return amountTxt;
    }

    public void setAmountTxt(String amountTxt) {
        this.amountTxt = amountTxt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trxId= " + trxId +
                ", amount= " + amountTxt +
                ", trxType= " + trxType +
                ", account= { IBAN : " + account.getAccountNumber() + ", Balance :" + account.getBalancetxt() + " }" +
                ", trxDate= " + trxDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return trxId.equals(that.trxId) && amount.equals(that.amount) && trxType == that.trxType && account.equals(that.account) && Objects.equals(trxDate, that.trxDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trxId, amount, trxType, account, trxDate);
    }
}
