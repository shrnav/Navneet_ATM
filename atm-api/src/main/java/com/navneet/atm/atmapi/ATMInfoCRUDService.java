package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.ATMInfo;
import com.navneet.atm.atmapi.exception.ATMException;
import com.navneet.atm.atmapi.repository.ATMInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
        InetAddress IP = null;
        try {
            IP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("IP of my system is := "+IP.getHostAddress());
        for (ATMInfo info : atmInfos) {
            return info.toString()+" coming from port ::"+(environment.getProperty("local.server.port")+" from host address ::"+IP.getHostAddress()+" Host Name"+IP.getHostName());
        }
        return null;
    }

    @PostMapping(value = "/atm/createATMInfo")
    public ResponseEntity<ATMInfo> createATMInfo(@RequestBody ATMInfo atmInfo) throws ATMException {

        ATMInfo atm = atmInfoRepository.save(atmInfo);

        if (atm == null) {
            throw new ATMException();
        } else {
            return new ResponseEntity<>(atm, HttpStatus.CREATED);
        }
    }

}
