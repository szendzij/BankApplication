package com.company;

public class BankAccount_COVID19_firma extends BankAccount_firma {

    public BankAccount_COVID19_firma() {
        super();
    }

    @Override
    public String toString() {
        return "Nazwa użytkownika: " + this.getName() + "\n" +
                "Numer regon: " + this.getREGON() + "\n" +
                "Aktualna dostepna ilość środków: " + this.getBalance() + "PLN";
    }

}
