package com.navneet.atm.atmapi.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ATMInfo {
    private @Id  Long initialAmount;
    private int noteOfFifties;
    private int noteOfTwenties;
    private int noteOfTens;
    private int noteOfFives;
    private Long remainingAmount;

    public ATMInfo(Long initialAmount, int noteOfFifties, int noteOfTwenties, int noteOfTens, int noteOfFives, Long remainingAmount) {
        this.initialAmount = initialAmount;
        this.noteOfFifties = noteOfFifties;
        this.noteOfTwenties = noteOfTwenties;
        this.noteOfTens = noteOfTens;
        this.noteOfFives = noteOfFives;
        this.remainingAmount = remainingAmount;
    }
}
