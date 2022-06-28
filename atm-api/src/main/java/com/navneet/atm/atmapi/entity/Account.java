package com.navneet.atm.atmapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
public class Account {
    private @Id @GeneratedValue int id;
    private Long acctNumber;
    private int pin;
    private Long openingBal;
    private Long overDraft;

    public Account(Long acctNumber, int pin, Long openingBal, Long overDraft) {
        this.acctNumber = acctNumber;
        this.pin = pin;
        this.openingBal = openingBal;
        this.overDraft = overDraft;
    }
}
