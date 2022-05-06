package com.mss.bankmngmtsys.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable  {
    private Long cliId;
    private String cliName;
    private List<Transaction> transactionHistory;

    public Client(Long cliId, String cliName) {
        this.cliId = cliId;
        this.cliName = cliName;
        this.transactionHistory = new ArrayList<>();
    }

    public Long getCliId() {
        return cliId;
    }

    public void setCliId(Long cliId) {
        this.cliId = cliId;
    }

    public String getCliName() {
        return cliName;
    }

    public void setCliName(String cliName) {
        this.cliName = cliName;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void  addInTransactionHistory(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    @Override
    public String toString() {
        return "Client{" +
                "cliId=" + cliId +
                ", cliName='" + cliName + '\'' +
                ", transactionHistory=" + transactionHistory +
                '}';
    }
}
