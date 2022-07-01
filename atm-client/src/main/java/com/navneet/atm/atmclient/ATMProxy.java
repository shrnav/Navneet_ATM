package com.navneet.atm.atmclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="atm-api")
public interface ATMProxy {
//    @RequestMapping(value = "/atm/{theatreName}/{city}/{movieName}/{screeningDate}/{screeningTime}",method = RequestMethod.GET)
//    public String getBMS(@PathVariable String theatreName, @PathVariable String city, @PathVariable String movieName, @PathVariable String screeningDate, @PathVariable String screeningTime);
//    @RequestMapping(value = "/atm/{theatreName}/{city}",method = RequestMethod.GET)
//    public String getBMS(@PathVariable String theatreName, @PathVariable String city);

    @RequestMapping(value="/atm/get")
    String getATMInfo();
}
