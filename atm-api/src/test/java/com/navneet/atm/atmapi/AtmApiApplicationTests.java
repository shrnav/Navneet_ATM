package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.ATMInfo;
import com.navneet.atm.atmapi.entity.Account;
import com.navneet.atm.atmapi.exception.ATMException;
import com.navneet.atm.atmapi.exception.AccountException;
import com.navneet.atm.atmapi.repository.ATMInfoRepository;
import com.navneet.atm.atmapi.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class AtmApiApplicationTests {

    AccountOperationService accountOperationService = new AccountOperationService();
    AccountCRUDService accountCRUDService = new AccountCRUDService();
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ATMInfoRepository atmInfoRepository;
    ATMInfoCRUDService atmInfoCRUDService = new ATMInfoCRUDService();

    @Test
    void contextLoads() {
    }



    @Test
    @Order(1)
    void testInsertATM() {
        ATMInfo atmInfo = new ATMInfo();
        atmInfo.setInitialAmount(1500L);
        atmInfo.setNoteOfFifties(10);
        atmInfo.setNoteOfTwenties(30);
        atmInfo.setNoteOfTens(30);
        atmInfo.setNoteOfFives(20);
        atmInfoRepository.save(atmInfo);
        Iterable<ATMInfo> all = atmInfoRepository.findAll();
        assertNotNull(all);

        assertNotNull(atmInfoCRUDService.insertATM(atmInfo.getInitialAmount(), atmInfo.getNoteOfFifties(),atmInfo.getNoteOfTwenties(),atmInfo.getNoteOfTens(),atmInfo.getNoteOfFives(),atmInfo.getRemainingAmount(),atmInfoRepository));

    }

    @Test
    @Order(2)
    void testGetATM() {

        assertNotNull(atmInfoRepository.findAll());
    }

    @Test
    @Order(3)
    void testCreateAccount() {
        Account account = new Account();
        account.setOpeningBal(900L);
        account.setOverDraft(200L);
        account.setAcctNumber(123456780L);
        account.setPin(2809);
        accountRepository.save(account);
        assertNotNull(accountRepository.findById(123456780L).get());
        //  account = new Account(123456789L,1234,800L,200L,800L);
        accountCRUDService.createAccount(123456789L, 1234, 800L, 800L, accountRepository);
        assertNotNull(accountRepository.findById(123456789L).get());
    }

    @Test
    @Order(4)
    void testGetAccount() {
        assertNotNull(accountCRUDService.getAccount(123456789L, accountRepository));
    }

    @Test
    @Order(5)
    void testAccountWithdraw() {
        Account account = accountRepository.findById(123456780L).get();

        try {
            assertNotNull(accountOperationService.getWithdrawal(123456780L, 2809, 200L, accountRepository));
        } catch (AccountException e) {
            e.printStackTrace();
        } catch (ATMException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(6)
    void testGetRemainingBalance() {
        Account account = accountRepository.findById(123456780L).get();
        try {
            String accountBalance = accountOperationService.getAccountBalance(account.getAcctNumber(), account.getPin(), accountRepository);
            log.info("accountBalance==>" + accountBalance);
            assertNotNull(accountBalance);
        } catch (AccountException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(7)
    void testDeleteAccount() {
        Account account = accountRepository.findById(123456789L).get();
//
        try {
            accountCRUDService.deleteAccount(account.getAcctNumber(), accountRepository);
        } catch (AccountException e) {
            e.printStackTrace();
        }


        assertEquals(Optional.empty(), accountRepository.findById(123456789L));
    }

}
