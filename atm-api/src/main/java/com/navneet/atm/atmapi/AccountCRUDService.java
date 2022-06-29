package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.Account;
import com.navneet.atm.atmapi.exception.ATMException;
import com.navneet.atm.atmapi.exception.AccountException;
import com.navneet.atm.atmapi.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class AccountCRUDService {

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(value = "/account/create/{accountNumber}/{pin}/{openingBal}/{overDraft}", method = RequestMethod.GET)
    public String createAccount(@PathVariable Long accountNumber, @PathVariable int pin, @PathVariable Long openingBal, @PathVariable Long overDraft) {

        //Initially opening balance will be equal to available balance
        Account account = new Account(accountNumber,pin,openingBal,overDraft,openingBal);
        account = accountRepository.save(account);
        log.info("Account :: "+ account +" has been created successfully.");
        return "Account:: "+accountNumber +" has been created successfully.";
    }

    @RequestMapping(value = "/account/delete/{accountNumber}", method = RequestMethod.GET)
    public String deleteAccount(@PathVariable Long accountNumber) throws AccountException {
        Optional<Account> byId = accountRepository.findById(accountNumber);
        if(byId.isPresent()) {
            accountRepository.deleteById(accountNumber);
            log.info("Account :: " + accountNumber + " has been deleted successfully.");
            return "Account:: " + accountNumber + " has been deleted successfully.";
        }
        else {
            throw new AccountException("This Account:: "+accountNumber+" does not exist.");
        }
    }
}
