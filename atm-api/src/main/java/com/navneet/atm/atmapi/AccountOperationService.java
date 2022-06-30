package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.Account;
import com.navneet.atm.atmapi.exception.ATMException;
import com.navneet.atm.atmapi.exception.AccountException;
import com.navneet.atm.atmapi.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.navneet.atm.atmapi.util.CommonConstants.*;

@Slf4j
@RestController
public class AccountOperationService {
    int count;
    int withDrawCount;
    Long overDraftAmount;
    @Autowired
    AccountRepository accountRepository;

    //Service to get the balance
    @GetMapping(value = "/account/getBal/{accountNumber}/{pin}")
    public String getAccountBalance(@PathVariable Long accountNumber, @PathVariable int pin, AccountRepository accountRepositoryTest) throws AccountException {
        if (null == accountRepository)
            accountRepository = accountRepositoryTest;
        Optional<Account> acctBal = accountRepository.findById(accountNumber);

        if (acctBal.isPresent()) {
            Account account = acctBal.get();
            count++;
            if (account.getPin() != pin) {
                if (count <= 3) {

                    log.info(RETRY_PIN + (THREE-count));
                    return RETRY_PIN + (THREE-count);
                } else {

                    throw new AccountException(RETRY_PIN_EXCEPTION);
                }
            }
            count = 0; //Needs to reset upon successful event.

            log.info(AVAILABLE_BALANCE + account.getAvailableBal());
            return AVAILABLE_BALANCE + account.getAvailableBal();
        } else {
            throw new AccountException(ACCOUNT_DOESNOT_EXIST+accountNumber);
        }
    }

    @GetMapping(value = "/account/getWithdraw/{accountNumber}/{pin}/{amount}")
    public String getWithdrawal(@PathVariable Long accountNumber, @PathVariable int pin, @PathVariable Long amount, AccountRepository accountRepositoryTest) throws AccountException, ATMException {

        if (amount % 5 != 0L)
            throw new ATMException(MULTIPLE_OF_FIVE);
        Optional<Account> acct;
        if (null == accountRepository) {
            accountRepository = accountRepositoryTest;
        }
        acct = accountRepository.findById(accountNumber);
        withDrawCount++;
        if (acct.isPresent()) {
            Account account = acct.get();

            if (account.getPin() != pin) {
                if (withDrawCount <= 3) {
                    log.info(RETRY_PIN+(3 - withDrawCount));
                    return RETRY_PIN+(3 - withDrawCount);
                } else {
                    throw new AccountException(RETRY_PIN_EXCEPTION);
                }

            }
            Long availableBalance = account.getAvailableBal();
            if (availableBalance >= 0) {
                availableBalance = account.getAvailableBal() - amount;
                account.setAvailableBal(availableBalance);
                accountRepository.save(account);

                log.info("Your " + accountNumber + " available balance is " + account.getAvailableBal() + "Currency===>>" + getCurrency(amount));
                withDrawCount = 0; //Needs to reset upon successful event.

                return "Your " + accountNumber + " available balance is " + account.getAvailableBal() + "Currency===>>" + getCurrency(amount);
            } else {
                overDraftAmount = account.getOverDraft();

                if (overDraftAmount >= 0L) {
                    overDraftAmount = availableBalance - overDraftAmount;
                    account.setOverDraft(overDraftAmount);
                    accountRepository.save(account);
                    return NO_MAIN_BALANCE + account.getAvailableBal();
                } else {
                    throw new AccountException(NO_BALANCE + account.getAcctNumber());
                }
            }
        } else {
            throw new AccountException(ACCOUNT_DOESNOT_EXIST+accountNumber);
        }

    }

    public Map<Integer, Integer> getCurrency(Long amount) {
        int[] notes = new int[]{50, 20, 10, 5};
        int[] noteCounter = new int[4];
        int amt = amount.intValue();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            if (amt >= notes[i]) {
                noteCounter[i] = amt / notes[i];
                amt = amt - noteCounter[i] * notes[i];
            }
        }

        for (int i = 0; i < 4; i++) {
            if (noteCounter[i] != 0) {

                map.put(notes[i], noteCounter[i]);

            }
        }
        return map;
    }
}
