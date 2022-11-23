package com.company;

public class BankAccount_COVID19_firma extends BankAccount {
    private String REGON = null;

    public BankAccount_COVID19_firma(String name, int id, double balance, int phoneNumber) {
        super(name, id, balance, phoneNumber);
    }

    public void setREGON(String REGON) {
        this.REGON = REGON;
    }

    public String getREGON() {
        return REGON;
    }

}
