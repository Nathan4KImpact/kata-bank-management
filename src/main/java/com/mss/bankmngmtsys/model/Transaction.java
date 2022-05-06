package com.mss.bankmngmtsys.model;

import com.mss.bankmngmtsys.utils.EnumTransactionType;

import javax.swing.text.DateFormatter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Transaction Class
 * @author RNF
 */
public class Transaction {
    private Long trxId;
    private BigDecimal amount;
    private EnumTransactionType trxType;
    private Account account;
    private LocalDate trxDate;

    public Long getTrxId() {
        return trxId;
    }

    public void setTrxId(Long trxId) {
        this.trxId = trxId;
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

    public LocalDate getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(LocalDate trxDate) {
        this.trxDate = trxDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trxId=" + trxId +
                ", amount=" + amount +
                ", trxType=" + trxType +
                ", account=" + account.getAccountNumber() +
                ", trxDate=" + trxDate +
                '}';
    }
}
