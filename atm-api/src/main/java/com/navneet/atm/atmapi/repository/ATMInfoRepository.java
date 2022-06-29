package com.navneet.atm.atmapi.repository;

import com.navneet.atm.atmapi.entity.ATMInfo;
import org.springframework.data.repository.CrudRepository;

public interface ATMInfoRepository extends CrudRepository<ATMInfo, Long> {
}
