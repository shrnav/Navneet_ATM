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

    public Long getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(Long acctNumber) {
        this.acctNumber = acctNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Long getOpeningBal() {
        return openingBal;
    }

    public void setOpeningBal(Long openingBal) {
        this.openingBal = openingBal;
    }

    public Long getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(Long overDraft) {
        this.overDraft = overDraft;
    }

    public Long getAvailableBal() {
        return availableBal;
    }

    public void setAvailableBal(Long availableBal) {
        this.availableBal = availableBal;
    }
}
