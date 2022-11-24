package com.company;

import javax.swing.*;

public class BankAccount_firma extends BankAccount {
    private String REGON = null;

    public BankAccount_firma(String name, int id, double balance) {
        super(name, id, balance);
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
        JOptionPane.showMessageDialog(null,
                "Nazwa użytkownika: " + this.getName() + "\n" +
                        "Identyfikator użytkownika: " + this.getId() + "\n" +
                        "Aktualna dostepna ilość środków: " + this.getBalance() + "PLN" + "\n" +
                        "Numer regon: " + this.getREGON());
        return null;
    }

}
