package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.Account;
import com.navneet.atm.atmapi.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountCRUDService {

    Logger logger = LoggerFactory.getLogger(AccountCRUDService.class);

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(value = "/account/create/{accountNumber}/{pin}/{openingBal}/{overDraft}", method = RequestMethod.GET)
    public String insertIntoAccount(@PathVariable Long accountNumber, @PathVariable int pin, @PathVariable Long openingBal, @PathVariable Long overDraft) {

        Account account = new Account(accountNumber,pin,openingBal,overDraft);
        account = accountRepository.save(account);
        logger.info("Account :: "+ account +" has been created successfully.");
        return "Account:: "+accountNumber +" has been created successfully.";
    }
}
