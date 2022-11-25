package com.company;

import javax.swing.*;
import java.io.Serializable;

public class BankAccount implements Serializable {
    private static int uid = 0;
    private String id;
    private double balance;
    private String name;


    public BankAccount() {
        this.id = String.valueOf(uid++);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void idRandomizer(BankAccount account) {
        account.setId(account.getName().toUpperCase() + "_00" + account.getId());
    }

    public void deposit(BankAccount account, double depositAmount) {
        account.balance += depositAmount;
        account.setBalance(account.balance);
        JOptionPane.showMessageDialog(null, "Wpływ: " + depositAmount + " PLN\n" + "Stan konta: " + account.balance + " PLN");
    }

    public void withdraw(BankAccount account, double withdrawAmount) {
        if (account.balance <= withdrawAmount) {
            JOptionPane.showMessageDialog(null, "Nie masz odpowiedniej ilości środków na koncie");
        } else {
            account.balance -= withdrawAmount;
            JOptionPane.showMessageDialog(null, "Wypłata: " + withdrawAmount + " PLN\n" + "Stan konta: " + account.balance + " PLN");
        }
    }

    public void info(BankAccount account) {
        JOptionPane.showMessageDialog(null, account.toString());
    }

    @Override
    public String toString() {
        return "Nazwa użytkownika: " + this.getName() + "\n" + "Identyfikator użytkownika: " + this.getId() + "\n" + "Aktualna dostępna ilość_środków: " + this.getBalance() + "PLN";
    }

}
