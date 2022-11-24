package com.company;

import javax.swing.*;

public class BankAccount_INT extends BankAccount{

    private String origin;

    public String getOrigin() {
        return origin;
    }

    public BankAccount_INT(String name, int id, double balance) {
        super(name, id, balance);
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        JOptionPane.showMessageDialog(null,
                "Nazwa użytkownika: " + this.getName() + "\n" +
                        "Identyfikator użytkownika: " + this.getId() + "\n" +
                        "Aktualna dostepna ilość środków: " + this.getBalance() + "PLN" + "\n" +
                        "Kraj pochodzenia: " + this.getOrigin());
        return null;
    }


}
