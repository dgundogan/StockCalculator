package com.nutmeg.model;

import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;

/**
 * Created by DG on 07/12/2017.
 */
public class Account {
    private String account;
    private LocalDate date;
    private TxnType txnType;
    private Double unit;
    private Double price;
    private String asset;

    public Account(String account, String date, String txnType, String unit, String price, String asset) {
        this.account = account.trim();
        this.date =  LocalDate.parse(date.trim(),BASIC_ISO_DATE);
        this.txnType = TxnType.valueOf(txnType.trim());
        this.unit = Double.valueOf(unit.trim());
        this.price = Double.valueOf(price.trim());
        this.asset = asset.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TxnType getTxnType() {
        return txnType;
    }

    public void setTxnType(TxnType txnType) {
        this.txnType = txnType;
    }

    public Double getUnit() {
        return unit;
    }

    public void setUnit(Double unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }
}
