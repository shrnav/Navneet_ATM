package com.navneet.atm.atmapi.util;

import com.navneet.atm.atmapi.entity.Account;

public class CommonConstants {
    public static final String RETRY_PIN = "You have entered invalid pin. Remaining Attempts: ";
    public static final String AVAILABLE_BALANCE = "Your available balance is: ";
    public static final String ACCOUNT_DOESNOT_EXIST = "This account does not exist: ";
    public static final String MULTIPLE_OF_FIVE = "Please enter the amount in multiple of five";
    public static final String NO_MAIN_BALANCE =     " Deducting the amount from overdraft,as no amount exist to disburse from main balance.Total Balance is: ";
    public static final String NO_BALANCE = "No amount available to disburse for the account number:: ";
    public static final String ACCOUNT_CREATED = "Account has been created successfully: ";
    public static final String ACCOUNT_DELETED = "Account has been deleted successfully: ";
    public static final int THREE = 3;

    public static final String RETRY_PIN_EXCEPTION = "You have entered invalid PIN three time. Your account has been blocked for the day!!!";
    public static final String ATM_INITIAL_AMOUNT = "ATM has been uploaded with initial amount: ";
}
