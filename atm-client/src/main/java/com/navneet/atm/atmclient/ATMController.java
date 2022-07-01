package com.navneet.atm.atmclient;

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
}
