package com.mss.bankmngmtsys;

import com.mss.bankmngmtsys.model.Account;
import com.mss.bankmngmtsys.model.Client;
import com.mss.bankmngmtsys.model.Transaction;
import com.mss.bankmngmtsys.service.BankService;
import com.mss.bankmngmtsys.utils.EnumTransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BankMngtTests {

    static BankService bankService;

    static Client client;
    static Account account;
    static Transaction transaction1;
    static Transaction transaction2;
    Transaction transaction3;
    Transaction transaction4 ;
    static String transactionsHistory;

    @BeforeAll
    public static void setUp(){
        bankService = new BankService();

        client = new Client(1L, "Raoul FONGANG");
        account = new Account(1L, client.getCliId(), "FRXXXXXXXXXXXXXX0001");
        transactionsHistory = "D:\\MesPROJETS\\Ingeniance\\devs\\kata-bank-management\\database\\transactionsHistory.json";

        transaction1 = new Transaction(1L, BigDecimal.TEN, EnumTransactionType.DEPOSIT, account);

        account.setBalance(BigDecimal.TEN);
        transaction2 = new Transaction(1L, BigDecimal.valueOf(5), EnumTransactionType.WITHDRAWAL, account);
    }

    /**
     * US 1: In order to save money As a bank client I want to make a deposit in my account
     */
     // Test case  1 : we make a 1st deposit in an empty account
    @Test
    public void testMakeDeposit(){
        BigDecimal expected = BigDecimal.TEN;
        BigDecimal result = bankService.makeTransactionIntoAccount(transaction1, EnumTransactionType.DEPOSIT, client);
        Assertions.assertEquals(expected, result);
    }

    /**
     * US 2: In order to retrieve some or all of my savings, As a bank client, I want to make a withdrawal from my account
     */
    // Test case 2  : we make a 1st Withdrawal of a value in an empty account
    @Test
    public void testMakeWithDrawal(){
        BigDecimal expected = BigDecimal.valueOf(5);
        BigDecimal result = bankService.makeTransactionIntoAccount(transaction2, EnumTransactionType.WITHDRAWAL, client);
        Assertions.assertEquals(expected, result);
    }


}
