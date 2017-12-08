package com.dgundogan.model;

public class Holding implements java.io.Serializable{
    private String asset;
    private double holding;

    public Holding(String asset, double holding) {
        this.asset = asset;
        this.holding = holding;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public double getHolding() {
        return holding;
    }

    public void setHoldings(double holding) {
        this.holding = holding;
    }

    public String toString() {
        return getAsset() + ":\t" + getHolding();
    }

    public void calculateHolding(Holding holding){
        this.holding += holding.holding;
    }

    public static Holding calculateHolding(Account account){
        return new Holding(account.getAsset(),calculatePrice(account));
    }

    public static Holding calculateHoldingCash(Account account){
        return new Holding("CASH",calculateCash(account));
    }

    public static double calculateCash(Account account){
        double cashPrice = 0;
        switch (account.getTxnType()){
            case DIV: cashPrice = account.getUnit();
                break;
            case BOT: cashPrice = -1*account.getPrice()*account.getUnit();
                break;
            case DEP: cashPrice = account.getUnit();
                break;
            case SLD: cashPrice = account.getPrice()*account.getUnit();
                break;
            case WDR:cashPrice = -1*account.getUnit();
                break;
        }

        return cashPrice;
    }

    public static double calculatePrice(Account account){
        double price = 0;
        switch (account.getTxnType()){
            case DIV: price = 0;
                break;
            case BOT: price = account.getUnit();
                break;
            case DEP: price = account.getUnit();
                break;
            case SLD: price = -1*account.getUnit();
                break;
            case WDR:price = account.getUnit();
                break;
        }

        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Holding holding1 = (Holding) o;
        return asset.equals(holding1.asset);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = asset.hashCode();
        temp = Double.doubleToLongBits(holding);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


}