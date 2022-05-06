package com.mss.bankmngmtsys.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mss.bankmngmtsys.model.Client;
import com.mss.bankmngmtsys.model.Transaction;
import com.mss.bankmngmtsys.utils.EnumTransactionType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankService {

    private Logger LOG = Logger.getLogger("BankService ");
    private final BigDecimal THRESHOLD_AMOUNT_VALUE = new BigDecimal(-50);
   private final String TRANSACTIONS_HISTORY_FILE = "D:\\MesPROJETS\\Ingeniance\\devs\\kata-bank-management\\database\\transactionsHistory.json";


    /**
     * register a new transaction (deposit or withdrawal) into the account specified
     * @param  trx
     * @param  trxType
     * @param client
     * @return account balance
     */

    public BigDecimal makeTransactionIntoAccount(Transaction trx, EnumTransactionType trxType, Client client){

        switch (trxType){
            case DEPOSIT :
                trx.getAccount().addToBalance(trx.getAmount());
                client.addInTransactionHistory(trx);
               // saveTrxToHistory(trx, client);
                break;
            case WITHDRAWAL :
                trx.getAccount().addToBalance(trx.getAmount().negate());
                if (trx.getAccount().getBalance().compareTo(THRESHOLD_AMOUNT_VALUE) < 0){
                    LOG.log(Level.WARNING, "Vous avez un solde négatif !");
                }
                client.addInTransactionHistory(trx);
                //saveTrxToHistory(trx, client);
                break;
        }
        return trx.getAccount().getBalance();
    }



    private boolean saveTrxToHistory(Transaction trx, Client client) {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String jsonString = gson.toJson(client);

        try {
            Path outputPath = Paths.get(TRANSACTIONS_HISTORY_FILE);
            Files.writeString (outputPath, jsonString, Charset.forName("UTF-8"));
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Erreur lors de la sauvegarde de la transaction");
            return false;
        }
        return true;
    }


    public List<Transaction> showAccountStatement(Client client){

        try  {
            Path path = Paths.get(TRANSACTIONS_HISTORY_FILE);
            String inputJsonContent = Files.readString(path);

            //Read JSON file
            Gson gson = new Gson();
            Client cli = gson.fromJson(inputJsonContent, Client.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client.getTransactionHistory();

    }
}
