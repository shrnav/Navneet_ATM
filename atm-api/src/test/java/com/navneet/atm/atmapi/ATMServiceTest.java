package com.navneet.atm.atmapi;

import com.navneet.atm.atmapi.entity.ATMInfo;
import com.navneet.atm.atmapi.repository.ATMInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ATMServiceTest {

    @Autowired
    ATMInfoRepository atmInfoRepository;

    @Test
    @Order(1)
    public void testInsertATM() {
        ATMInfo atmInfo = new ATMInfo();
        atmInfo.setInitialAmount(1500L);
        atmInfo.setNoteOfFifties(10);
        atmInfo.setNoteOfTwenties(30);
        atmInfo.setNoteOfTens(30);
        atmInfo.setNoteOfFives(20);
        atmInfoRepository.save(atmInfo);
        Iterable<ATMInfo> all = atmInfoRepository.findAll();

        assertNotNull(all);
    }

    @Test
    @Order(2)
    public void testGetATM() {

        assertNotNull(atmInfoRepository.findAll());
    }
}
