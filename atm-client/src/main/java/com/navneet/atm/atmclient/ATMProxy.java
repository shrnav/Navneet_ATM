package com.navneet.atm.atmclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.security.auth.login.AccountException;

@FeignClient(name="atm-api")
public interface ATMProxy {

    @RequestMapping(value="/atm/get")
    String getATMInfo();

    @RequestMapping(value="/account/get/{accountNumber}")
    String getAccountInfo(@PathVariable Long accountNumber);

    @RequestMapping(value = "/account/delete/{accountNumber}")
    String deleteAccount(@PathVariable Long accountNumber);

    @RequestMapping("/account/getWithdraw/{accountNumber}/{pin}/{amount}")
    String getWithdrawal(@PathVariable Long accountNumber, @PathVariable int pin, @PathVariable Long amount);

    @RequestMapping(value = "/account/getBal/{accountNumber}/{pin}")
    String getAccountBalance(@PathVariable Long accountNumber, @PathVariable int pin);

}
