package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.Account;
import com.navneet.atm.atmapi.exception.ATMException;
import com.navneet.atm.atmapi.exception.AccountException;
import com.navneet.atm.atmapi.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTest {
    AccountOperationService accountOperationService = new AccountOperationService();
    AccountCRUDService accountCRUDService = new AccountCRUDService();
    @Autowired
    AccountRepository accountRepository;

    @Test
    @Order(1)
    public void testCreateAccount() {
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
    @Order(2)
    public void testGetAccount() {
        assertNotNull(accountCRUDService.getAccount(123456789L, accountRepository));
    }

    @Test
    @Order(3)
    public void testAccountWithdraw() {
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
    @Order(4)
    public void testGetRemainingBalance() {
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
    @Order(4)
    public void testDeleteAccount() {
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
