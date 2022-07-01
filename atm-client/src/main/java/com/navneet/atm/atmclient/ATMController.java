package com.navneet.atm.atmclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMController {

    @Autowired
    private ATMProxy proxy;

    @GetMapping("/atm-client/atmino")
    public String getATMInfo() {
        System.out.println("debu........................1");
        return proxy.getATMInfo();
    }
//    @GetMapping("/bms-client/{theatreName}/{city}")
//    public String getResponseForTheatreByCityAndMovie(@PathVariable String theatreName, @PathVariable String city) {
//        System.out.println("debu1........................1");
//        return proxy.getBMS(theatreName,city);
//    }
}
