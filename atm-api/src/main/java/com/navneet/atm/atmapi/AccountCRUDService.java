package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.Account;
import com.navneet.atm.atmapi.exception.AccountException;
import com.navneet.atm.atmapi.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.navneet.atm.atmapi.util.CommonConstants.*;

@RestController
@Slf4j
public class AccountCRUDService {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping(value = "/account/create/{accountNumber}/{pin}/{openingBal}/{overDraft}")
    public String createAccount(@PathVariable Long accountNumber, @PathVariable int pin, @PathVariable Long openingBal, @PathVariable Long overDraft, AccountRepository accountRepositoryTest) {

        //Initially opening balance will be equal to available balance
        if (null == accountRepository) {
            accountRepository = accountRepositoryTest;
        }
        Account account = new Account(accountNumber, pin, openingBal, overDraft, openingBal);
        account = accountRepository.save(account);
        log.info(ACCOUNT_CREATED+accountNumber);
        return ACCOUNT_CREATED+accountNumber;
    }

    @GetMapping(value = "/account/get/{accountNumber}")
    public String getAccount(@PathVariable Long accountNumber, AccountRepository accountRepositoryTest) {

        //Initially opening balance will be equal to available balance
        if (null == accountRepository) {
            accountRepository = accountRepositoryTest;
        }
        Account account = accountRepository.findById(accountNumber).get();


        account = accountRepository.save(account);
        log.info("Account :: " + account);
        return "Account:: " + account;
    }

    @GetMapping(value = "/account/delete/{accountNumber}")
    public String deleteAccount(@PathVariable Long accountNumber, AccountRepository accountRepositoryTest) throws AccountException {
        if (null == accountRepository)
            accountRepository = accountRepositoryTest;
        Optional<Account> byId = this.accountRepository.findById(accountNumber);
        if (byId.isPresent()) {
            this.accountRepository.deleteById(accountNumber);
            log.info(ACCOUNT_DELETED+accountNumber);
            return ACCOUNT_DELETED+accountNumber;
        } else {
            throw new AccountException(ACCOUNT_DOESNOT_EXIST+accountNumber);
        }
    }
}
