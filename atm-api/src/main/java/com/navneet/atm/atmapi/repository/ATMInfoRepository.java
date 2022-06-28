package com.navneet.atm.atmapi.repository;

import com.navneet.atm.atmapi.entity.ATMInfo;
import com.navneet.atm.atmapi.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface ATMInfoRepository extends CrudRepository<ATMInfo, Long> {
}
