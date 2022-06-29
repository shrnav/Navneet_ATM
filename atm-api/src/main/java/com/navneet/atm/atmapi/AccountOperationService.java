package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.ATMInfo;
import com.navneet.atm.atmapi.entity.Account;
import com.navneet.atm.atmapi.exception.ATMException;
import com.navneet.atm.atmapi.exception.AccountException;
import com.navneet.atm.atmapi.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class AccountOperationService {
    int count, withDrawCount;
    Long overDraftAmount;
    @Autowired
    AccountRepository accountRepository;

    //Service to get the balance
    @RequestMapping(value = "/account/getBal/{accountNumber}/{pin}")
    public String getAccountBalance(@PathVariable Long accountNumber, @PathVariable int pin, AccountRepository accountRepositoryTest) throws AccountException {
        if(null == accountRepository)
            accountRepository = accountRepositoryTest;
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
    public String getWithdrawal(@PathVariable Long accountNumber, @PathVariable int pin, @PathVariable Long amount,AccountRepository accountRepositoryTest) throws AccountException, ATMException {

        if(amount%5!=0L)
            throw new ATMException("Please enter the amount in multiple of 5");
        Optional<Account> acct;
        if(null==accountRepository) {
             accountRepository = accountRepositoryTest;
        }
        acct = accountRepository.findById(accountNumber);
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
            Long availableBalance = account.getAvailableBal();
            if(availableBalance>=0) {
                availableBalance = account.getAvailableBal() - amount;
                account.setAvailableBal(availableBalance);
                accountRepository.save(account);

                log.info("Your " + accountNumber + " available balance is " + account.getAvailableBal()+"Currency===>>"+getCurrency(amount));
                withDrawCount = 0; //Needs to reset upon successful event.

                return "Your " + accountNumber + " available balance is " + account.getAvailableBal()+"Currency===>>"+getCurrency(amount);
            }
            else
            {
                overDraftAmount = account.getOverDraft();
                Long tempAmount = overDraftAmount;
                if(overDraftAmount>=0L) {
                    overDraftAmount= availableBalance - overDraftAmount;
                    account.setOverDraft(overDraftAmount);
                    accountRepository.save(account);
                    return "AccountNumber " + accountNumber + " has no amount to disburse. Deducting the amount from overdraft. Total balance is "+account.getAvailableBal() ;
                }
                else
                {
                    throw new AccountException("No amount available to disburse for the account number:: "+account.getAcctNumber());
                }
            }
        } else {
            throw new AccountException("This Account:: " + accountNumber + " does not exist.");
        }

    }

    public Map<Integer,Integer> getCurrency(Long amount) throws ATMException {
        int[] notes = new int[]{ 50,20,10,5 };
        int[] noteCounter = new int[4];
        int amt = amount.intValue();

        System.out.println("amt==="+amt);
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            if (amt >= notes[i]) {
                noteCounter[i] = amt / notes[i];
                amt = amt - noteCounter[i] * notes[i];
            }
        }
        System.out.println("Currency Count ->");
        for (int i = 0; i < 4; i++) {
            if (noteCounter[i] != 0) {
                System.out.println(notes[i] + " : "
                        + noteCounter[i]);
                map.put(notes[i],noteCounter[i]);

            }
        }
        return map;
    }
}
