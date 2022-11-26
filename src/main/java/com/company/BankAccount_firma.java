package com.company;

public class BankAccount_firma extends BankAccount {
    private String REGON = null;

    public BankAccount_firma() {
        super();
        this.REGON = REGON;
    }

    public void setREGON(String REGON) {
        this.REGON = REGON;
    }

    public String getREGON() {
        return REGON;
    }

    @Override
    public String toString() {
        return "Nazwa użytkownika: " + this.getName() + "\n" +
                "Numer regon: " + this.getREGON() + "\n" +
                "Aktualna dostepna ilość środków: " + this.getBalance() + "PLN";
    }

}
