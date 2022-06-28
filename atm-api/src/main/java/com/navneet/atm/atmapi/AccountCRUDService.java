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

import java.util.Optional;

@RestController
public class AccountCRUDService {

    Logger logger = LoggerFactory.getLogger(AccountCRUDService.class);

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(value = "/account/create/{accountNumber}/{pin}/{openingBal}/{overDraft}/{availableBal}", method = RequestMethod.GET)
    public String createAccount(@PathVariable Long accountNumber, @PathVariable int pin, @PathVariable Long openingBal, @PathVariable Long overDraft, @PathVariable Long availableBal) {

        Account account = new Account(accountNumber,pin,openingBal,overDraft,availableBal);
        account = accountRepository.save(account);
        logger.info("Account :: "+ account +" has been created successfully.");
        return "Account:: "+accountNumber +" has been created successfully.";
    }

    @RequestMapping(value = "/account/delete/{accountNumber}", method = RequestMethod.GET)
    public String deleteAccount(@PathVariable Long accountNumber) {
        Optional<Account> byId = accountRepository.findById(accountNumber);
        if(byId.isPresent()) {
            accountRepository.deleteById(accountNumber);
            logger.info("Account :: " + accountNumber + " has been deleted successfully.");
            return "Account:: " + accountNumber + " has been deleted successfully.";
        }
        else {
            return "This Account:: "+accountNumber+" does not exist.";
        }
    }
}
