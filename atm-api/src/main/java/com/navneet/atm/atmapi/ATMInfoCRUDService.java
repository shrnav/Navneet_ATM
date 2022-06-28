package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.ATMInfo;
import com.navneet.atm.atmapi.repository.ATMInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ATMInfoCRUDService {
    Logger logger = LoggerFactory.getLogger(ATMInfoCRUDService.class);

    @Autowired
    ATMInfoRepository atmInfoRepository;
    @RequestMapping(value = "/atm/create/{initAmt}/{fifties}/{twenties}/{tens}/{fives}/{remainAmt}", method = RequestMethod.GET)
    public String insertATM(@PathVariable Long initAmt, @PathVariable int fifties, @PathVariable int twenties, @PathVariable int tens, @PathVariable int fives, @PathVariable Long remainAmt){
        ATMInfo atmInfo = new ATMInfo(initAmt,fifties,twenties,tens,fives,remainAmt);
        atmInfoRepository.save(atmInfo);
        logger.info("ATM has been uploaded with "+initAmt+" amount.");
        return "ATM has been uploaded with "+initAmt+" amount.";
    }

}
