package com.mss.bankmgnt;

import com.mss.bankmgnt.model.Account;
import com.mss.bankmgnt.model.Client;
import com.mss.bankmgnt.model.Transaction;
import com.mss.bankmgnt.service.BankService;
import com.mss.bankmgnt.utils.EnumTransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

/**
 * BankMngtTests Class
 * @author RNF
 */
public class BankMngtTests {

    static BankService bankService;

    static Client client;
    static Account account;
    static Transaction transaction1;
    static Transaction transaction2;
    static Transaction transaction3;
    static Transaction transaction4 ;
    static BigDecimal resultDeposit, resultWithDrawal,resultAfter4trx;

    @BeforeAll
    public static void setUp(){
        bankService = new BankService();

        client = new Client(1L, "Raoul FONGANG");
        account = new Account(1L, client.getCliId(), "FRXX XXXX XXXX XXXX 0001");

        transaction1 = new Transaction(1L, BigDecimal.TEN, EnumTransactionType.DEPOSIT, account);
        resultDeposit = bankService.makeATransactionIntoAccount(transaction1, client);

        Account updatedAccount1 = account.clone();
        transaction2 = new Transaction(2L, BigDecimal.valueOf(5), EnumTransactionType.WITHDRAWAL, updatedAccount1);
        resultWithDrawal = bankService.makeATransactionIntoAccount(transaction2, client);

        Account updatedAccount2 = updatedAccount1.clone();
        transaction3 = new Transaction(3L, BigDecimal.valueOf(15), EnumTransactionType.WITHDRAWAL, updatedAccount2);
        bankService.makeATransactionIntoAccount(transaction3, client);

        Account updatedAccount3 = updatedAccount2.clone();
        transaction4 = new Transaction(4L, BigDecimal.valueOf(50), EnumTransactionType.DEPOSIT, updatedAccount3);
        resultAfter4trx = bankService.makeATransactionIntoAccount(transaction4, client);
    }

    /**
     * US 1: In order to save money As a bank client I want to make a deposit in my account
     */
     // Test case  1: we make a 1st deposit of an amount of 10 in an empty account
    @Test
    public void testMakeDeposit(){
        BigDecimal expected = BigDecimal.TEN;
        Assertions.assertEquals(expected, resultDeposit);
    }

    /**
     * US 2: In order to retrieve some or all of my savings, As a bank client, I want to make a withdrawal from my account
     */
    // Test case 2  : we make a 1st Withdrawal from the previous account balance which should be equal to 10
    @Test
    public void testMakeWithDrawal() {
        BigDecimal expected = BigDecimal.valueOf(5);
        Assertions.assertEquals(expected, resultWithDrawal);
    }

    /**
     * US 3: In order to check my operations As a bank client I want to see the history (operation, date, amount, balance) of my operations
     */
    // Test case 3: We check the balance after the 4 transactions
    @Test
    public void testBalanceAfter4Transactions() {
        BigDecimal expected = BigDecimal.valueOf(40); // balance should be equal to 45
        Assertions.assertEquals(expected, resultAfter4trx);
    }

    // Test case 4: We check the ability to retrieve ALL the previous transactions after storage
    @Test
    public void testSizeHistoryStatement() {
        int expected = client.getTransactionHistory().size(); // 04 trxs
        int result = bankService.showAccountHistoryOfTransactions(client).size();

        Assertions.assertEquals(expected, result);
    }

    // Test case 5: We check list of transactions introduced and the list of all the previous transactions retrieved from storage
    @Test
    public void testShowHistoryStatement() {

        List<Transaction> expected = client.getTransactionHistory();
        List<Transaction> result = bankService.showAccountHistoryOfTransactions(client);

        IntStream.rangeClosed(0, expected.size()-1)
            .forEach(i -> {
                Assertions.assertEquals(expected.get(i), result.get(i));
            });
    }
}
