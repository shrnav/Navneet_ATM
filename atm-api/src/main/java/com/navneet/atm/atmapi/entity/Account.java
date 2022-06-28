package com.navneet.atm.atmapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
public class Account {
    private @Id Long acctNumber;
    private int pin;
    private Long openingBal;
    private Long overDraft;
    private Long availableBal = 0L;

    public Account(Long acctNumber, int pin, Long openingBal, Long overDraft,Long availableBal) {
        this.acctNumber = acctNumber;
        this.pin = pin;
        this.openingBal = openingBal;
        this.overDraft = overDraft;
        this.availableBal = availableBal;
    }
}
