package com.mss.bankmngmtsys;

import com.mss.bankmngmtsys.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankMngtTests {

    BankService bankService;

    @BeforeEach
    public void setUp(){
        bankService = new BankService();

    }

    @Test
    public void testMakeDeposit(){

    }

}
