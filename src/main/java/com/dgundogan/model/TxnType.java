package com.dgundogan.model;

/**
 * Created by DG on 07/12/2017.
 */
public enum TxnType {
    BOT, //Stock Purchase -> For the Stock purchase(sale) transactions you should deduct(increase) the cash balance of the customer by the value of the transaction increment(decrement) the holdings for that stock
    SLD, //Stock sale
    DIV, //Dividend payment -> For a dividend you should increase the customers cash balance by the given amount
    DEP, //Deposit -> For a deposit(withdrawal) you should increase(decrease) the customers cash balance by the given amount
    WDR //Withdrawal
}
