package com.mss.bankmgnt.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mss.bankmgnt.model.Client;
import com.mss.bankmgnt.model.Transaction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankDao {
    private transient NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
    ResourceBundle config = ResourceBundle.getBundle("config");

    private Logger LOG = Logger.getLogger("BankDao");
    private static String TRANSACTIONS_HISTORY_FILE;


    public boolean save(Transaction trx, Client client) {
        TRANSACTIONS_HISTORY_FILE  = MessageFormat.format(config.getString("transactions.history.filePath"), client.getCliId());
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String jsonString = gson.toJson(client);

        try {
            Path outputPath = Paths.get(TRANSACTIONS_HISTORY_FILE);
            Files.write(outputPath, jsonString.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Erreur lors de la sauvegarde de la transaction");
            return false;
        }
        return true;
    }

    public List<Transaction> show(Client client){
        TRANSACTIONS_HISTORY_FILE  = MessageFormat.format(config.getString("transactions.history.filePath"), client.getCliId());
        Client cli = new Client();
        try  {
            Path path = Paths.get(TRANSACTIONS_HISTORY_FILE);
            String inputJsonContent = String.join("\n", Files.readAllLines(path,StandardCharsets.UTF_8));

            //Read JSON file
            Gson gson = new Gson();
            cli = gson.fromJson(inputJsonContent, Client.class);
            cli.getTransactionHistory()
                    .forEach(trx -> {
                        try {
                            trx.setAmount(BigDecimal.valueOf( currencyFormat.parse(trx.getAmountTxt()).longValue() ));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cli.getTransactionHistory();
    }
}
