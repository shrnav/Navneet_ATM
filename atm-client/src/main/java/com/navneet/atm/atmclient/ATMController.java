package com.navneet.atm.atmclient;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMController {

    @Autowired
    private ATMProxy proxy;

    @GetMapping("/atm-client/atminfo")
    public String getATMInfo() {
                return proxy.getATMInfo();
    }

    @GetMapping("/account-client/accountInfo/{accountNumber}")
    public String getAccountInfo(@PathVariable Long accountNumber) {
        return proxy.getAccountInfo(accountNumber);
    }
    @GetMapping("/account-client/deleteAccount/{accountNumber}")
    public String deleteAccount(@PathVariable Long accountNumber) {
        return proxy.deleteAccount(accountNumber);
    }

    //Account operation services
    @GetMapping("/account-client/withdrawAccount/{accountNumber}/{pin}/{amount}")
    public String getWithdrawal(@PathVariable Long accountNumber,@PathVariable int pin, @PathVariable Long amount) {
        return proxy.getWithdrawal(accountNumber,pin,amount);
    }

    @GetMapping("/account-client/getBalance/{accountNumber}/{pin}")
    public String getBalance(@PathVariable Long accountNumber,@PathVariable int pin) {
        return proxy.getAccountBalance(accountNumber,pin);
    }
}
