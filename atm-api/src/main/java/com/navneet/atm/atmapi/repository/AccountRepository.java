package com.navneet.atm.atmapi.repository;

import com.navneet.atm.atmapi.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
