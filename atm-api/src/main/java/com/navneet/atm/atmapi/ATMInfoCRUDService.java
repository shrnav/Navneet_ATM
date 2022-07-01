package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.ATMInfo;
import com.navneet.atm.atmapi.repository.ATMInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.navneet.atm.atmapi.util.CommonConstants.ATM_INITIAL_AMOUNT;

@RestController
@Slf4j
public class ATMInfoCRUDService {

    //However we will usually add one row in ATMInfo table, but in future we can add more rows on te basis of date.
    @Autowired
    ATMInfoRepository atmInfoRepository;
    @Autowired
    Environment environment;

    @GetMapping(value = "/atm/create/{initAmt}/{fifties}/{twenties}/{tens}/{fives}/{remainAmt}")
    public String insertATM(@PathVariable Long initAmt, @PathVariable int fifties, @PathVariable int twenties, @PathVariable int tens, @PathVariable int fives, @PathVariable Long remainAmt, ATMInfoRepository atmInfoRepositoryTest) {
        ATMInfo atmInfo = new ATMInfo(initAmt, fifties, twenties, tens, fives, remainAmt);
        if(this.atmInfoRepository == null)
            this.atmInfoRepository = atmInfoRepositoryTest;
        this.atmInfoRepository.save(atmInfo);
        log.info(ATM_INITIAL_AMOUNT + initAmt);
        return ATM_INITIAL_AMOUNT+ initAmt;
    }

    @GetMapping(value = "/atm/get")
    public String getATMInfo() {
        Iterable<ATMInfo> atmInfos = atmInfoRepository.findAll();
        for (ATMInfo info : atmInfos) {
            return info.toString()+" coming from port ::"+(environment.getProperty("local.server.port"));
        }
        return null;
    }
}
