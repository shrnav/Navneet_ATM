package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.Account;
import com.navneet.atm.atmapi.exception.ATMException;
import com.navneet.atm.atmapi.exception.AccountException;
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
    private int count;

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
    public String deleteAccount(@PathVariable Long accountNumber) throws AccountException {
        Optional<Account> byId = accountRepository.findById(accountNumber);
        if(byId.isPresent()) {
            accountRepository.deleteById(accountNumber);
            logger.info("Account :: " + accountNumber + " has been deleted successfully.");
            return "Account:: " + accountNumber + " has been deleted successfully.";
        }
        else {
            throw new AccountException("This Account:: "+accountNumber+" does not exist.");
        }
    }

    @RequestMapping(value = "/account/getBal/{accountNumber}/{pin}")
     public String getAccountBalance(@PathVariable Long accountNumber,@PathVariable int pin) throws AccountException {
        Optional<Account> acctBal = accountRepository.findById(accountNumber);
        if(acctBal.isPresent()) {
            Account account = acctBal.get();
            System.out.println(account);
            //TODO Add the pin check
            count++;
            if (account.getPin() != pin){
                if(count < 4) {
                    logger.info("You have entered wrong pin." + (4 - count) + " more attempts are remaining for the day");
                    return "You have entered wrong pin." + (4 - count) + " more attempts are remaining for the day";
            } else
                {

                throw new AccountException("You have entered invalid PIN three time. Your account has been blocked for the day!!!");
            }
        }

            logger.info("Your "+ accountNumber +" available balance is "+account.getAvailableBal());
            return "Your "+ accountNumber +" available balance is "+account.getAvailableBal();
        }
        else
        {
            throw new AccountException("This Account:: "+accountNumber+" does not exist.");
        }
    }
}
