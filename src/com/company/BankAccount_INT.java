package com.company;

import javax.swing.*;
import java.math.BigDecimal;

public class BankAccount_INT extends BankAccount{

    private String origin;

    public String getOrigin() {
        return origin;
    }

    public BankAccount_INT(String name, int id, double balance, int phoneNumber) {
        super(name, id, balance, phoneNumber);
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        JOptionPane.showMessageDialog(null,
                "Nazwa użytkownika: " + this.getName() + "\n" +
                        "Numer telefonu: " + this.getPhoneNumber() + "\n" +
                        "Identyfikator użytkownika: " + this.getId() + "\n" +
                        "Aktualna dostepna ilość środków: " + this.getBalance() + "PLN" + "\n" +
                        "Kraj pochodzenia: " + this.getOrigin());
        return null;
    }


}
