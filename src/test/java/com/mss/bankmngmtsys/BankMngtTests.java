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
    Transaction transaction2;
    Transaction transaction3;
    Transaction transaction4 ;
    static String transactionsHistory;

    @BeforeAll
    public static void setUp(){
        bankService = new BankService();

        client = new Client(1L, "Raoul FONGANG");
        account = new Account(1L, client.getCliId(), "FRXX XXXX XXXX 0001");
        transaction1 = new Transaction(1L, BigDecimal.TEN, EnumTransactionType.DEPOSIT, account);
        transactionsHistory = "D:\\MesPROJETS\\Ingeniance\\devs\\kata-bank-management\\database\\transactionsHistory.json";
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


}
