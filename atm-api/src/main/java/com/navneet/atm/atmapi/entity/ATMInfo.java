package com.navneet.atm.atmapi.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ATMInfo {
    private @Id
    Long initialAmount;
    private int noteOfFifties;
    private int noteOfTwenties;
    private int noteOfTens;
    private int noteOfFives;
    private Long remainingAmount;
}
