package com.mss.bankmgnt.service;

import com.mss.bankmgnt.dao.BankDao;
import com.mss.bankmgnt.model.Client;
import com.mss.bankmgnt.model.Transaction;
import com.mss.bankmgnt.utils.EnumTransactionType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BankService Class
 * @author RNF
 */
public class BankService {

    private BankDao dao = new BankDao();
    private Logger LOG = Logger.getLogger("BankService");
    private final BigDecimal THRESHOLD_AMOUNT_VALUE = new BigDecimal(-50);


    /**
     * Register a new transaction (deposit or withdrawal) into the account specified
     * @param  trx
     * @param client
     * @return account balance
     */
    public BigDecimal makeATransactionIntoAccount(Transaction trx, Client client){

        EnumTransactionType trxType = trx.getTrxType();
        switch (trxType){
            case DEPOSIT :
                trx.getAccount().addToBalance(trx.getAmount());
                client.addInTransactionHistory(trx);
                saveTrxToHistory(trx, client);
                break;
            case WITHDRAWAL :
                trx.getAccount().addToBalance(trx.getAmount().negate());
                if (trx.getAccount().getBalance().compareTo(BigDecimal.valueOf(0)) < 0){
                    LOG.log(Level.WARNING, client.getCliName() + " vous avez un découvert de " + trx.getAccount().getBalance());
                }
                if (trx.getAccount().getBalance().compareTo(THRESHOLD_AMOUNT_VALUE) < 0){
                    LOG.log(Level.SEVERE, "Votre découvert ne permet plus de de faire de retrait !");
                } else {
                    client.addInTransactionHistory(trx);
                    saveTrxToHistory(trx, client);
                }

                break;
        }
        return trx.getAccount().getBalance();
    }


    /**
     * Store the list of transaction with the client (in a json file)
     * @param trx
     * @param client
     * @return
     */
    private boolean saveTrxToHistory(Transaction trx, Client client) {
        return dao.save(trx, client);
    }

    /**
     * Retrieve the history of prerecorded transactions
     * @param cliHisto
     * @return List of client's transactions retrieved from the storage
     */
    public List<Transaction> showAccountHistoryOfTransactions(Client cliHisto){
        return  dao.show(cliHisto);
    }
}
