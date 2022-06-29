package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.Account;
import com.navneet.atm.atmapi.exception.AccountException;
import com.navneet.atm.atmapi.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class AccountOperationService {
    int count, withDrawCount;
    @Autowired
    AccountRepository accountRepository;

    //Service to get the balance
    @RequestMapping(value = "/account/getBal/{accountNumber}/{pin}")
    public String getAccountBalance(@PathVariable Long accountNumber, @PathVariable int pin) throws AccountException {
        Optional<Account> acctBal = accountRepository.findById(accountNumber);

        if (acctBal.isPresent()) {
            Account account = acctBal.get();
            count++;
            if (account.getPin() != pin) {
                if (count <= 3) {

                    log.info("You have entered invalid pin." + (3 - count) + " more attempts are remaining for the day");
                    return "You have entered wrong pin." + (3 - count) + " more attempts are remaining for the day";
                } else {

                    throw new AccountException("You have entered invalid PIN three time. Your account has been blocked for the day!!!");
                }
            }
            count = 0; //Needs to reset upon successful event.
            log.info("Your " + accountNumber + " available balance is " + account.getAvailableBal());
            return "Your " + accountNumber + " available balance is " + account.getAvailableBal();
        } else {
            throw new AccountException("This Account:: " + accountNumber + " does not exist.");
        }
    }

    @RequestMapping(value = "/account/getWithdraw/{accountNumber}/{pin}/{amount}")
    public String getWithdrawal(@PathVariable Long accountNumber, @PathVariable int pin, @PathVariable Long amount) throws AccountException {
        Optional<Account> acct = accountRepository.findById(accountNumber);
        withDrawCount++;
        if (acct.isPresent()) {
            Account account = acct.get();

            if (account.getPin() != pin) {
                if (withDrawCount <= 3) {
                    log.info("You have entered invalid pin." + (3 - withDrawCount) + " more attempts are remaining for the day");
                    return "You have entered wrong pin." + (3 - withDrawCount) + " more attempts are remaining for the day";
                } else {
                    throw new AccountException("You have entered invalid PIN three time. Your account has been blocked for the day!!!");
                }

            }

            Long availableBalance = account.getAvailableBal() - amount;
            account.setAvailableBal(availableBalance);
            accountRepository.save(account);
            log.info("Your " + accountNumber + " available balance is " + account.getAvailableBal());
            withDrawCount = 0; //Needs to reset upon successful event.
            return "Your " + accountNumber + " available balance is " + account.getAvailableBal();
        } else {
            throw new AccountException("This Account:: " + accountNumber + " does not exist.");
        }

    }
}
