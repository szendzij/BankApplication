package com.company;

public class BankAccount_INT extends BankAccount {

    private String origin;

    public BankAccount_INT() {
        super();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Nazwa użytkownika: " + this.getName() + "\n" +
                "ID: " + this.getId() + "\n" +
                "Aktualna dostepna ilość środków: " + this.getBalance() + "PLN" + "\n" +
                "Kraj pochodzenia: " + this.getOrigin();
    }

}
